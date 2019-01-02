package com.dscunikom.android.sekolahqu.detail.acara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
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
    SessionManager sessionManager;
    ImageView ivFavorite;

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
        id_acara = getIntent().getStringExtra("id_acara");
        presenter.getDetailAcara(id_acara);
        presenter.addFavorite(id_acara);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionManager = new SessionManager(this);
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH_NOTIF);
        FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);
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
    public void showDetailAcara(AcaraModel model) {
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

    }
}
