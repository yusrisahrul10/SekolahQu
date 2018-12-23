package com.dscunikom.android.sekolahqu.detail.prestasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.prestasi.SpesifikSekolah;

public class DetailPrestasiActivity extends MvpActivity<DetailPrestasiPresenter> implements DetailPrestasiView {

    String id;
    ImageView imgDetail;
    TextView tvJudul , tvIsi;
    ProgressBar progressBar;
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
        progressBar = findViewById(R.id.progress_detail_prestasi);
        id = getIntent().getStringExtra("id_prestasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter.getDetailPrestasi(id);
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
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showDetailPrestasi(SpesifikSekolah model) {
        tvIsi.setText(model.getDeskripsi());
        tvJudul.setText(model.getNamaPrestasi());
        Glide.with(getApplicationContext())
                .load("http://sekolahqu.dscunikom.com/uploads/prestasi/"+model.getImage())
                .into(imgDetail);
    }

    @Override
    public void showDetatailFailed(String message) {

    }
}