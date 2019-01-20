package com.dscunikom.android.sekolahqu.detail.ekskul;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;

public class DetailEkskulActivity extends MvpActivity<DetailEkskulPresenter> implements DetailEkskulView {
    String id_ekskul;
    ImageView imgEskul;
    TextView tvJudul,tvDesc, tvPembina, tvNamaPembina, tvKetua, tvNamaKetua;
    SwipeRefreshLayout swipeRefresh;
    ProgressBar progressBar;
    @Override
    protected DetailEkskulPresenter createPresenter() {
        return new DetailEkskulPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ekskul);
        tvJudul = findViewById(R.id.txtEskul);
        tvDesc = findViewById(R.id.txtDesEskul);
        imgEskul = findViewById(R.id.imgEskul);
        swipeRefresh = findViewById(R.id.swipe_detail_ekskul);
        progressBar = findViewById(R.id.progress_bar_detail_ekskul);
        tvPembina = findViewById(R.id.tv_pembina);
        tvNamaPembina = findViewById(R.id.tv_pembina_ekskul);
        tvKetua = findViewById(R.id.tv_ketua);
        tvNamaKetua = findViewById(R.id.tv_ketua_ekskul);

        id_ekskul = getIntent().getStringExtra("id_ekskul");
        presenter.getDetailEkskul(id_ekskul);
        Log.e("Ekskul", ""+id_ekskul);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDetailEkskul(id_ekskul));
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
        imgEskul.setVisibility(View.INVISIBLE);
        tvJudul.setVisibility(View.GONE);
        tvDesc.setVisibility(View.GONE);
        tvPembina.setVisibility(View.GONE);
        tvNamaPembina.setVisibility(View.GONE);
        tvKetua.setVisibility(View.GONE);
        tvNamaKetua.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        imgEskul.setVisibility(View.VISIBLE);
        tvJudul.setVisibility(View.VISIBLE);
        tvDesc.setVisibility(View.VISIBLE);
        tvPembina.setVisibility(View.VISIBLE);
        tvNamaPembina.setVisibility(View.VISIBLE);
        tvKetua.setVisibility(View.VISIBLE);
        tvNamaKetua.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailEkskul(Ekskul model) {
        tvJudul.setText(model.getNamaEkskul());
        tvDesc.setText(model.getDeskripsi());
        tvNamaPembina.setText(model.getPembina());
        tvNamaKetua.setText(model.getKetua());
        Glide.with(this)
                .load("http://sekolahqu.dscunikom.com/uploads/ekskul/" + model.getImage())
                .into(imgEskul);
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDetailEkskulFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvDesc.setVisibility(View.GONE);
        tvPembina.setVisibility(View.GONE);
        tvNamaPembina.setVisibility(View.GONE);
        tvKetua.setVisibility(View.GONE);
        tvNamaKetua.setVisibility(View.GONE);
        imgEskul.setVisibility(View.VISIBLE);
        tvJudul.setVisibility(View.VISIBLE);
        Glide.with(getApplicationContext())
                .load(R.drawable.empty)
                .into(imgEskul);
        tvJudul.setText("Tidak Ada Data");
    }
}
