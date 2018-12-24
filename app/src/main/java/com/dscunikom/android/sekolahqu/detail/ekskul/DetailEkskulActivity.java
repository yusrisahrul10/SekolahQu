package com.dscunikom.android.sekolahqu.detail.ekskul;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;

public class DetailEkskulActivity extends MvpActivity<DetailEkskulPresenter> implements DetailEkskulView {
    String id_ekskul;
    ImageView imgEskul;
    TextView tvJudul,tvDesc;
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
        id_ekskul = getIntent().getStringExtra("id_ekskul");
        presenter.getDetailEkskul(id_ekskul);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDetailEkskul(Ekskul model) {
        tvJudul.setText(model.getNamaEkskul());
        tvDesc.setText(model.getDeskripsi());
        Glide.with(this)
                .load("http://sekolahqu.dscunikom.com/uploads/ekskul/" + model.getImage())
                .into(imgEskul);
    }

    @Override
    public void showDetatailFailed(String message) {
        
    }
}
