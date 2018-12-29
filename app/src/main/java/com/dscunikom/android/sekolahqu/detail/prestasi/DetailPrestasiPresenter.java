package com.dscunikom.android.sekolahqu.detail.prestasi;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.prestasi.SpesifikSekolah;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class DetailPrestasiPresenter extends BasePresenter<DetailPrestasiView> {
    public DetailPrestasiPresenter(DetailPrestasiView view) {
        super.attachView(view);
    }

    void getDetailPrestasi(String id_prestasi){
        view.showLoading();
        addSubscribe(apiStores.getDetailPrestasi(id_prestasi), new NetworkCallback<SpesifikSekolah>() {
            @Override
            public void onSuccess(SpesifikSekolah model) {
                view.showDetailPrestasi(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    void addFavorite(String id_prestasi) {
        addSubscribe(apiStores.getDetailPrestasi(id_prestasi), new NetworkCallback<SpesifikSekolah>() {
            @Override
            public void onSuccess(SpesifikSekolah model) {
                view.addFavoritePrestasi(model);
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
