package com.dscunikom.android.sekolahqu.favorite;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.acara.DetailAcaraActivity;
import com.dscunikom.android.sekolahqu.detail.berita.DetailBeritaActivity;
import com.dscunikom.android.sekolahqu.detail.prestasi.DetailPrestasiActivity;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.prestasi.SpesifikSekolah;

public class FavoritePresenter extends BasePresenter<FavoriteView> {

    public FavoritePresenter(FavoriteView view){
        super.attachView(view);
    }

    void getIdBerita(BeritaModel model, Activity activity){
        Intent intent = new Intent(activity,DetailBeritaActivity.class);
        intent.putExtra("id_berita",model.getIdBerita());
        view.moveToActivity(intent);
    }

    void getIdAcara(AcaraModel model , Activity activity){
        Intent intent = new Intent(activity,DetailAcaraActivity.class);
        intent.putExtra("id_acara",model.getIdAcara());
        view.moveToActivity(intent);
    }

    void getIdToPrestasi(SpesifikSekolah prestasi, Activity activity) {
        Intent intent = new Intent(activity, DetailPrestasiActivity.class);
        intent.putExtra("id_prestasi", prestasi.getIdPrestasi());
        view.moveToActivity(intent);
    }
}
