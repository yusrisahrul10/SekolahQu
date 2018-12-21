package com.dscunikom.android.sekolahqu.detail.berita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;

public class DetailBeritaActivity extends MvpActivity<DetailBeritaPresenter> implements DetailBeritaView {
    String id_berita;
    TextView tvJudul,tvIsi;
    ImageView imgDetail;
    @Override
    protected DetailBeritaPresenter createPresenter() {
        return new DetailBeritaPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        tvJudul = findViewById(R.id.txtJudulBerita);
        tvIsi = findViewById(R.id.txtIsiBerita);
        imgDetail = findViewById(R.id.imageDetailBerita);
        id_berita = getIntent().getStringExtra("id_berita");
        presenter.getDetailBerita(id_berita);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDetailBerita(BeritaModel model) {
        tvJudul.setText(model.getNamaBerita());
        tvIsi.setText(model.getDeskripsi());
        Glide.with(getApplicationContext())
                .load("http://sekolahqu.dscunikom.com/uploads/berita/"+model.getImage())
                .into(imgDetail);
    }

    @Override
    public void showDetatailFailed(String message) {

    }
}
