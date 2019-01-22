package com.dscunikom.android.sekolahqu.home.sekolah.fasilitas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.FasilitasAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.fasilitas.Fasilitas;
import com.dscunikom.android.sekolahqu.model.fasilitas.FasilitasResponse;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.HashMap;
import java.util.List;

public class FasilitasActivity extends MvpActivity<FasilitasPresenter> implements FasilitasView {
    private List<Fasilitas> list;
    @BindView(R.id.rv_fasilitas)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_fasilitas)
    ProgressBar progressBar;
    @BindView(R.id.tv_kosong_fasilitas)
    TextView tvDataKosong;
    @BindView(R.id.swipe_fasilitas)
    SwipeRefreshLayout swipeRefresh;

    SessionManager sessionManager;

    @Override
    protected FasilitasPresenter createPresenter() {
        return new FasilitasPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        sessionManager = new SessionManager(this);
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getListFasilitas(id_sekolah);
        swipeRefresh.setOnRefreshListener(() -> presenter.getListFasilitas(id_sekolah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return true;
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdtoDetailFasilitas(list.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdtoDetailFasilitas(list.get(position), activity);
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
    public void showListFasilitas(FasilitasResponse model) {
        swipeRefresh.setRefreshing(false);
        this.list = model.getResult();
        FasilitasAdapter adapter = new FasilitasAdapter(list, R.layout.item_ekskul_fasilitas, getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void showListFasilitasFailed(String message) {
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
