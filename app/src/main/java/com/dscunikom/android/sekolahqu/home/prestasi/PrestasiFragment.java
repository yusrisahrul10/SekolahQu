package com.dscunikom.android.sekolahqu.home.prestasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.adapter.PrestasiAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.HashMap;
import java.util.List;

public class PrestasiFragment extends MvpFragment<PrestasiPresenter> implements PrestasiView {

    RecyclerView recyclerView;
    private List<Prestasi> mList;

    SessionManager sessionManager;
//    @BindView(R.id.image_fragment_prestasi)
    ImageView imgAwardsNew;
    TextView tvAwardsNew, tvDataKosong;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected PrestasiPresenter createPresenter() {
        return new PrestasiPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_prestasi, container, false);
        tvAwardsNew = rootView.findViewById(R.id.text_fragment_prestasi);
        imgAwardsNew = rootView.findViewById(R.id.image_fragment_prestasi);
        tvDataKosong = rootView.findViewById(R.id.tv_kosong_prestasi);
        progressBar = rootView.findViewById(R.id.progress_bar_prestasi);

        //init presenter disini , entah kenapa variable presenter selalu null
        presenter = createPresenter();
        //jadi harus di panggil terus menerus bapukkkk
        recyclerView = rootView.findViewById(R.id.rv_awards);
        swipeRefresh = rootView.findViewById(R.id.swipe_prestasi);


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
        presenter.getDataPrestasi(id_sekolah);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDataPrestasi(id_sekolah));
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToPrestasi(mList.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdToPrestasi(mList.get(position), activity);
            }
        });
    }

    private void clickFirstPrestasi(PrestasiResponse model){
        presenter.getIdToPrestasi(model.getFirstData().get(0), activity);
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.GONE);
        tvAwardsNew.setVisibility(View.INVISIBLE);
        imgAwardsNew.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            tvDataKosong.setVisibility(View.GONE);
            tvAwardsNew.setVisibility(View.VISIBLE);
            imgAwardsNew.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListPrestasi(PrestasiResponse model) {
        swipeRefresh.setRefreshing(false);
        this.mList = model.getSpesifikSekolah();
            Log.e("List Prestasi2", "jumlah " +mList.size());
            this.mList.remove(mList.get(0));
            recyclerView.setAdapter(new PrestasiAdapter(mList,R.layout.item_content,this.getActivity()));
            Glide.with(this.getActivity())
                    .load("http://sekolahqu.dscunikom.com/uploads/prestasi/"+model.getFirstData().get(0).getImage())
                    .into(imgAwardsNew);
            tvAwardsNew.setText(model.getFirstData().get(0).getNamaPrestasi());
            imgAwardsNew.setOnClickListener(v -> clickFirstPrestasi(model));


    }

    @Override
    public void showListPrestasiFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.VISIBLE);
        tvAwardsNew.setVisibility(View.INVISIBLE);
        imgAwardsNew.setVisibility(View.INVISIBLE);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
