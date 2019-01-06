package com.dscunikom.android.sekolahqu.home.sekolah.ekskul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.EkskulAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;
import com.dscunikom.android.sekolahqu.model.ekskul.EkskulResponse;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.HashMap;
import java.util.List;

public class EkskulActivity extends MvpActivity<EkskulPresenter> implements EkskulView {

    private List<Ekskul> list;
    @BindView(R.id.rv_ekskul)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_ekskul)
    ProgressBar progressBar;
    @BindView(R.id.tv_kosong_ekskul)
    TextView tvDataKosong;
    @BindView(R.id.swipe_ekskul)
    SwipeRefreshLayout swipeRefresh;

    SessionManager sessionManager;

    @Override
    protected EkskulPresenter createPresenter() {
        return new EkskulPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekskul);
        sessionManager = new SessionManager(this);
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getEkskulFasilitas(id_sekolah);

        swipeRefresh.setOnRefreshListener(() -> presenter.getEkskulFasilitas(id_sekolah));
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToEkskulActivity(list.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdToEkskulActivity(list.get(position), activity);
            }
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        if (list.size() == 0) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            tvDataKosong.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            tvDataKosong.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListEkskul(EkskulResponse model) {
        swipeRefresh.setRefreshing(false);
        this.list = model.getSpesifikSekolah();

            EkskulAdapter adapter = new EkskulAdapter(list, R.layout.item_ekskul_fasilitas, getApplicationContext());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

    }

    @Override
    public void showListEkskulFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.VISIBLE);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
