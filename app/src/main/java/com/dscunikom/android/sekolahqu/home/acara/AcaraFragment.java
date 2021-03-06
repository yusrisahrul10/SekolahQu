package com.dscunikom.android.sekolahqu.home.acara;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.adapter.AcaraAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.List;

public class AcaraFragment extends MvpFragment<AcaraPresenter> implements AcaraView {
    RecyclerView recyclerView;
    private List<AcaraModel> mList;
    ImageView imgAcaraNews;
    TextView tvAcaraNews, tvDataKosong;
    SessionManager sessionManager;
    SwipeRefreshLayout swipeRefresh;
    ProgressBar progressBar;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_acara, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        mFirebaseAnalytics.setCurrentScreen(this.getActivity(), getActivity().getClass().getSimpleName(), getActivity().getClass().getSimpleName());
        presenter = createPresenter();
        recyclerView = rootView.findViewById(R.id.rv_events);
        tvAcaraNews = rootView.findViewById(R.id.text_fragment_acara);
        imgAcaraNews = rootView.findViewById(R.id.image_fragment_acara);
        swipeRefresh = rootView.findViewById(R.id.swipe_acara);
        tvDataKosong = rootView.findViewById(R.id.tv_kosong_acara);
        progressBar = rootView.findViewById(R.id.progress_bar_acara);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
//        FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);
        presenter.getDataAcara(id_sekolah);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDataAcara(id_sekolah));
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdAcara(mList.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdAcara(mList.get(position), activity);
            }
        });
    }

    private void clickFirstAcara(AcaraResponse model) {
        presenter.getIdAcara(model.getmFirstData().get(0), activity);
    }


    @Override
    protected AcaraPresenter createPresenter() {
        return new AcaraPresenter(this);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.GONE);
        tvAcaraNews.setVisibility(View.INVISIBLE);
        imgAcaraNews.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        tvDataKosong.setVisibility(View.GONE);
        tvAcaraNews.setVisibility(View.VISIBLE);
        imgAcaraNews.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListAcara(AcaraResponse model) {
        swipeRefresh.setRefreshing(false);
        this.mList = model.getSpesifikSekolah();
            this.mList.remove(mList.get(0));
            recyclerView.setAdapter(new AcaraAdapter(mList,R.layout.item_content,this.getActivity()));
            Glide.with(this.getActivity())
                    .load("http://sekolahqu.dscunikom.com/uploads/acara/"+model.getmFirstData().get(0).getImage())
                    .into(imgAcaraNews);
            tvAcaraNews.setText(model.getmFirstData().get(0).getNamaAcara());
            imgAcaraNews.setOnClickListener(v -> clickFirstAcara(model));


    }

    @Override
    public void showListAcaraFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.VISIBLE);
        tvAcaraNews.setVisibility(View.INVISIBLE);
        imgAcaraNews.setVisibility(View.INVISIBLE);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
