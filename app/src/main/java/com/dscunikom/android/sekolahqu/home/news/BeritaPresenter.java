package com.dscunikom.android.sekolahqu.home.news;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
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
}
