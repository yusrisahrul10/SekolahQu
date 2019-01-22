package com.dscunikom.android.sekolahqu.detail.berita;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class DetailBeritaPresenter extends BasePresenter<DetailBeritaView> {
    public DetailBeritaPresenter(DetailBeritaView view) {
        super.attachView(view);
    }
    void getDetailBerita(String id_berita){
        view.showLoading();
        addSubscribe(apiStores.getDetailBerita(id_berita), new NetworkCallback<BeritaModel>() {
            @Override
            public void onSuccess(BeritaModel model) {
                view.showDetailBerita(model);
                view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                view.showDetailBeritaFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void addFavorite(String id_berita) {
        addSubscribe(apiStores.getDetailBerita(id_berita), new NetworkCallback<BeritaModel>() {
            @Override
            public void onSuccess(BeritaModel model) {
                view.addFavoriteBerita(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {

            }
        });
    }


}
