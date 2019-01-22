package com.dscunikom.android.sekolahqu.detail.fasilitas;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.DetailFasilitasAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.Gambar;
import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.GambarResponse;

import java.util.List;

public class DetailFasilitasActivity extends MvpActivity<DetailFasilitasPresenter> implements DetailFasilitasView {
    RecyclerView recyclerView;
    String id_fasilitas;
    private List<Gambar> mList;
    ProgressBar progressBar;
    TextView tvDataKosong;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected DetailFasilitasPresenter createPresenter() {
        return new DetailFasilitasPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fasilitas);
        id_fasilitas = getIntent().getStringExtra("id_fasilitas");
        recyclerView = findViewById(R.id.rv_detail_fasilitas);
        progressBar = findViewById(R.id.progress_bar_detail_fasilitas);
        tvDataKosong = findViewById(R.id.tv_kosong_detail_fasilitas);
        swipeRefresh = findViewById(R.id.swipe_detail_fasilitas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getDetailFasilitas(id_fasilitas);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDetailFasilitas(id_fasilitas));
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

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        tvDataKosong.setVisibility(View.GONE);
    }

    @Override
    public void showDetailFasilitas(GambarResponse model) {
        swipeRefresh.setRefreshing(false);
        this.mList = model.getGambar();
        recyclerView.setAdapter(new DetailFasilitasAdapter(mList,R.layout.item_detail_fasilitas,this));

    }

    @Override
    public void showDetailFasilitasFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvDataKosong.setVisibility(View.VISIBLE);
    }
}
