package com.dscunikom.android.sekolahqu.home.news;

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
import com.dscunikom.android.sekolahqu.adapter.BeritaAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.HashMap;
import java.util.List;

public class BeritaFragment extends MvpFragment<BeritaPresenter> implements BeritaView {
    private List<BeritaModel> mList;
    TextView tvBeritaNews, tvDataKosong;
    ImageView imgBeritaNews;
    RecyclerView recyclerView;
    SessionManager sessionManager;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefresh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_berita, container, false);
        tvBeritaNews = rootView.findViewById(R.id.text_fragment_berita);
        imgBeritaNews = rootView.findViewById(R.id.image_fragment_berita);
        tvDataKosong = rootView.findViewById(R.id.tv_kosong_berita);
        progressBar = rootView.findViewById(R.id.progress_bar_berita);
        presenter = createPresenter();
        recyclerView = rootView.findViewById(R.id.rv_berita);
        swipeRefresh = rootView.findViewById(R.id.swipe_berita);
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
        presenter.getDataBerita(id_sekolah);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataBerita(id_sekolah);
            }
        });
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdBerita(mList.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdBerita(mList.get(position), activity);
            }
        });
    }



    private void clickFirstBerita(BeritaResponse model){
        presenter.getIdBerita(model.getmFirstData().get(0), activity);
    }

    @Override
    protected BeritaPresenter createPresenter() {
        return new BeritaPresenter(this);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.GONE);
        tvBeritaNews.setVisibility(View.INVISIBLE);
        imgBeritaNews.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        tvDataKosong.setVisibility(View.GONE);
        tvBeritaNews.setVisibility(View.VISIBLE);
        imgBeritaNews.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListBerita(BeritaResponse model) {
            swipeRefresh.setRefreshing(false);
            this.mList = model.getSpesifikSekolah();
            this.mList.remove(mList.get(0));
            tvBeritaNews.setText(model.getmFirstData().get(0).getNamaBerita());
            Glide.with(this.getActivity())
                    .load("http://sekolahqu.dscunikom.com/uploads/berita/"+model.getmFirstData().get(0).getImage())
                    .into(imgBeritaNews);

            recyclerView.setAdapter(new BeritaAdapter(mList,R.layout.item_content,this.getActivity()));
            imgBeritaNews.setOnClickListener(v -> clickFirstBerita(model));
    }

    @Override
    public void showListBeritaFailed(String message) {
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.VISIBLE);
        tvBeritaNews.setVisibility(View.INVISIBLE);
        imgBeritaNews.setVisibility(View.INVISIBLE);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
