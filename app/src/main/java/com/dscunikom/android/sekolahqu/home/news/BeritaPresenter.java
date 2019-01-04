package com.dscunikom.android.sekolahqu.home.news;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.berita.DetailBeritaActivity;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class BeritaPresenter extends BasePresenter<BeritaView> {
    public BeritaPresenter(BeritaView view) {
        super.attachView(view);
    }
    void getDataBerita(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getListBerita(id_sekolah), new NetworkCallback<BeritaResponse>() {
            @Override
            public void onSuccess(BeritaResponse model) {
                view.showListBerita(model);
                view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                view.showListBeritaFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }
    void getIdBerita(BeritaModel model, Activity activity){
        Intent intent = new Intent(activity,DetailBeritaActivity.class);
        intent.putExtra("id_berita",model.getIdBerita());
        view.moveToActivity(intent);
    }
}
