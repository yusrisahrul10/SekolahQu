package com.dscunikom.android.sekolahqu.detail.acara;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.database.AcaraHelper;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class DetailAcaraActivity extends MvpActivity<DetailAcaraPresenter> implements DetailAcaraView {
    String id_acara;
    TextView tvJudul,tvIsi;
    ImageView imgDetailAcara;
//    SessionManager sessionManager;
    ImageView ivFavorite;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefresh;
//    View view;

    boolean exists;
    boolean checked = false;
    @Override
    protected DetailAcaraPresenter createPresenter() {
        return new DetailAcaraPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acara);
        tvIsi = findViewById(R.id.txtIsiAcara);
        tvJudul = findViewById(R.id.txtJudulAcara);
        imgDetailAcara = findViewById(R.id.imageDetailAcara);
        ivFavorite = findViewById(R.id.ib_favorite_acara);
        progressBar = findViewById(R.id.progress_bar_detail_acara);
        swipeRefresh = findViewById(R.id.swipe_detail_acara);
//        view = findViewById(R.id.view_detail_acara);





        id_acara = getIntent().getStringExtra("id_acara");
        presenter.getDetailAcara(id_acara);
        presenter.addFavorite(id_acara);
        swipeRefresh.setOnRefreshListener(() -> presenter.getDetailAcara(id_acara));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        sessionManager = new SessionManager(this);
//        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
//        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH_NOTIF);
//        FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);

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
        imgDetailAcara.setVisibility(View.GONE);
        ivFavorite.setVisibility(View.GONE);
//        view.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        tvIsi.setVisibility(View.VISIBLE);
        tvJudul.setVisibility(View.VISIBLE);
        imgDetailAcara.setVisibility(View.VISIBLE);
        ivFavorite.setVisibility(View.VISIBLE);
//        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailAcara(AcaraModel model) {
        swipeRefresh.setRefreshing(false);
        tvJudul.setText(model.getNamaAcara());
        tvIsi.setText(model.getDeskripsi());
        Glide.with(getApplicationContext())
                .load("http://sekolahqu.dscunikom.com/uploads/acara/"+model.getImage())
                .into(imgDetailAcara);
    }

    @Override
    public void addFavoriteAcara(AcaraModel model) {
        AcaraHelper acaraHelper = new AcaraHelper(getApplicationContext());
        acaraHelper.open();
        exists = acaraHelper.checkIsAcaraAlreadyFavorited(model.getIdAcara());
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
                acaraHelper.beginTransaction();
                acaraHelper.insertAcara(model);
                acaraHelper.setTransactionSuccess();
                acaraHelper.endTransaction();
            } else if (checked){
                checked = false;
                exists = false;
                ivFavorite.setImageResource(R.drawable.ic_favorite);
                acaraHelper.deleteAcara(model.getIdAcara());
            } else {
                exists = false;
                ivFavorite.setImageResource(R.drawable.ic_favorite);
                acaraHelper.deleteAcara(model.getIdAcara());
            }
        });
    }

    @Override
    public void showDetatailFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        tvIsi.setVisibility(View.GONE);
        tvJudul.setVisibility(View.VISIBLE);
        imgDetailAcara.setVisibility(View.VISIBLE);
        ivFavorite.setVisibility(View.GONE);
        tvJudul.setText("Tidak Ada Data");
        imgDetailAcara.setImageResource(R.drawable.empty);
    }
}
