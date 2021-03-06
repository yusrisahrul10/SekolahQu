package com.dscunikom.android.sekolahqu.detail.berita;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.database.BeritaHelper;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class DetailBeritaActivity extends MvpActivity<DetailBeritaPresenter> implements DetailBeritaView {
    String id_berita;
    TextView tvJudul,tvIsi;
    ImageView imgDetail;
//    SessionManager sessionManager;
    ImageView ibFavorite;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefresh;

    boolean exists;
    boolean checked = false;

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
        ibFavorite = findViewById(R.id.ib_favorite_berita);
        progressBar = findViewById(R.id.progress_bar_detail_berita);
        swipeRefresh = findViewById(R.id.swipe_detail_berita);


        id_berita = getIntent().getStringExtra("id_berita");
        presenter.getDetailBerita(id_berita);
        presenter.addFavorite(id_berita);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDetailBerita(id_berita));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        sessionManager = new SessionManager(this);
//        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
//        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);

//        if(sessionManager.creatSession()){
//            FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);
//        }
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
        ibFavorite.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        tvIsi.setVisibility(View.VISIBLE);
        tvJudul.setVisibility(View.VISIBLE);
        imgDetail.setVisibility(View.VISIBLE);
        ibFavorite.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailBerita(BeritaModel model) {
        swipeRefresh.setRefreshing(false);
        tvJudul.setText(model.getNamaBerita());
        tvIsi.setText(model.getDeskripsi());
        Glide.with(getApplicationContext())
                .load("http://sekolahqu.dscunikom.com/uploads/berita/"+model.getImage())
                .into(imgDetail);
    }

    @Override
    public void addFavoriteBerita(BeritaModel model) {

        BeritaHelper beritaHelper = new BeritaHelper(getApplicationContext());
        beritaHelper.open();
        exists = beritaHelper.checkIsBeritaAlreadyFavorited(model.getIdBerita());
        if (exists) {
            ibFavorite.setImageResource(R.drawable.ic_favorite_black);
        } else {
            ibFavorite.setImageResource(R.drawable.ic_favorite);
        }

        ibFavorite.setOnClickListener(view -> {
            if (!exists && !checked){
                exists = true;
                checked = true;
                ibFavorite.setImageResource(R.drawable.ic_favorite_black);
                beritaHelper.beginTransaction();
                beritaHelper.insertBerita(model);
                beritaHelper.setTransactionSuccess();
                beritaHelper.endTransaction();
                Log.e("Exists1 : ", String.valueOf(exists));
                Log.e("Checked1 : ", String.valueOf(checked));
            } else if (checked){
                checked = false;
                exists = false;
                ibFavorite.setImageResource(R.drawable.ic_favorite);
                beritaHelper.deleteBerita(model.getIdBerita());
                Log.e("Exists2 : ", String.valueOf(exists));
                Log.e("Checked2 : ", String.valueOf(checked));
            } else {
                exists = false;
                ibFavorite.setImageResource(R.drawable.ic_favorite);
                beritaHelper.deleteBerita(model.getIdBerita());
                Log.e("Exists3 : ", String.valueOf(exists));
                Log.e("Checked3 : ", String.valueOf(checked));
            }
        });

    }



    @Override
    public void showDetailBeritaFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvIsi.setVisibility(View.GONE);
        tvJudul.setVisibility(View.VISIBLE);
        imgDetail.setVisibility(View.VISIBLE);
        ibFavorite.setVisibility(View.GONE);

        tvJudul.setText("Tidak Ada Data");
        imgDetail.setImageResource(R.drawable.empty);
    }
}
