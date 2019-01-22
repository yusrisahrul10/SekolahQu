package com.dscunikom.android.sekolahqu.home.sekolah.sekolah;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.prestasi.DetailPrestasiActivity;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiLimit;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class SekolahPresenter extends BasePresenter<SekolahView> {
    public SekolahPresenter(SekolahView view) {
        super.attachView(view);
    }

    void getImagePrestasi(String id) {
        addSubscribe(apiStores.getPrestasiLimit(id), new NetworkCallback<PrestasiLimit>() {
            @Override
            public void onSuccess(PrestasiLimit model) {
                view.showImagePrestasi(model);
            }

            @Override
            public void onFailure(String message) {
                view.showImagePrestasiFailed(message);
            }

            @Override
            public void onFinish() {
            }
        });
    }

    void getLogoSekolah(String id_sekolah) {
        addSubscribe(apiStores.getDetailSekolah(id_sekolah), new NetworkCallback<Sekolah>() {
            @Override
            public void onSuccess(Sekolah model) {
                view.showImageLogoSekolah(model);
            }

            @Override
            public void onFailure(String message) {
                view.showImageLogoSekolahFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void getIdToPrestasi(Prestasi prestasi, Activity activity) {
        Intent intent = new Intent(activity, DetailPrestasiActivity.class);
        intent.putExtra("id_prestasi", prestasi.getIdPrestasi());
        view.moveToActivity(intent);
    }
}
