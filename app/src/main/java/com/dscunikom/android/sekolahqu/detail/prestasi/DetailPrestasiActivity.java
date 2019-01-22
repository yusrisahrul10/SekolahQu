package com.dscunikom.android.sekolahqu.detail.prestasi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.database.PrestasiHelper;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;

public class DetailPrestasiActivity extends MvpActivity<DetailPrestasiPresenter> implements DetailPrestasiView {

    String id;
    ImageView imgDetail;
    TextView tvJudul , tvIsi;
    ProgressBar progressBar;
    ImageView ivFavorite;
    SwipeRefreshLayout swipeRefresh;

    boolean exists;
    boolean checked = false;

    @Override
    protected DetailPrestasiPresenter createPresenter() {
        return new DetailPrestasiPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prestasi);
        imgDetail = findViewById(R.id.imageDetail);
        tvJudul = findViewById(R.id.txtJudul);
        tvIsi = findViewById(R.id.txtIsiPrestasi);
        ivFavorite = findViewById(R.id.ib_favorite_prestasi);
        progressBar = findViewById(R.id.progress_detail_prestasi);
        swipeRefresh = findViewById(R.id.swipe_detail_prestasi);

        id = getIntent().getStringExtra("id_prestasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter.getDetailPrestasi(id);
        presenter.addFavorite(id);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDetailPrestasi(id));
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
        tvIsi.setVisibility(View.GONE);
        tvJudul.setVisibility(View.GONE);
        imgDetail.setVisibility(View.GONE);
        ivFavorite.setVisibility(View.GONE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        tvIsi.setVisibility(View.VISIBLE);
        tvJudul.setVisibility(View.VISIBLE);
        imgDetail.setVisibility(View.VISIBLE);
        ivFavorite.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailPrestasi(Prestasi model) {
        swipeRefresh.setRefreshing(false);
        tvIsi.setText(model.getDeskripsi());
        tvJudul.setText(model.getNamaPrestasi());
        Glide.with(getApplicationContext())
                .load("http://sekolahqu.dscunikom.com/uploads/prestasi/"+model.getImage())
                .into(imgDetail);
    }

    @Override
    public void addFavoritePrestasi(Prestasi model) {
        PrestasiHelper prestasiHelper = new PrestasiHelper(getApplicationContext());
        prestasiHelper.open();
        exists = prestasiHelper.checkIsPrestasiAlreadyFavorited(model.getIdPrestasi());
        if (exists) {
            ivFavorite.setImageResource(R.drawable.ic_favorite_black);
        } else {
            ivFavorite.setImageResource(R.drawable.ic_favorite);
        }

        ivFavorite.setOnClickListener(view -> {
            if (!exists && !checked){
                exists = true;
                checked = true;
                ivFavorite.setImageResource(R.drawable.ic_favorite_black);
                prestasiHelper.beginTransaction();
                prestasiHelper.insertPrestasi(model);
                prestasiHelper.setTransactionSuccess();
                prestasiHelper.endTransaction();
            } else if (checked){
                checked = false;
                exists = false;
                ivFavorite.setImageResource(R.drawable.ic_favorite);
                prestasiHelper.deletePrestasi(model.getIdPrestasi());
            } else {
                exists = false;
                ivFavorite.setImageResource(R.drawable.ic_favorite);
                prestasiHelper.deletePrestasi(model.getIdPrestasi());
            }
        });
    }

    @Override
    public void showDetailPrestasiFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvIsi.setVisibility(View.GONE);
        tvJudul.setVisibility(View.VISIBLE);
        imgDetail.setVisibility(View.VISIBLE);
        ivFavorite.setVisibility(View.GONE);

        tvJudul.setText("Tidak Ada Data");
        imgDetail.setImageResource(R.drawable.empty);
    }
}
