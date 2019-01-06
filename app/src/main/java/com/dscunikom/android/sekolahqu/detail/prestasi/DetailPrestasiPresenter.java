package com.dscunikom.android.sekolahqu.detail.prestasi;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class DetailPrestasiPresenter extends BasePresenter<DetailPrestasiView> {
    public DetailPrestasiPresenter(DetailPrestasiView view) {
        super.attachView(view);
    }

    void getDetailPrestasi(String id_prestasi){
        view.showLoading();
        addSubscribe(apiStores.getDetailPrestasi(id_prestasi), new NetworkCallback<Prestasi>() {
            @Override
            public void onSuccess(Prestasi model) {
                view.showDetailPrestasi(model);
                view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                view.showDetailPrestasiFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void addFavorite(String id_prestasi) {
        addSubscribe(apiStores.getDetailPrestasi(id_prestasi), new NetworkCallback<Prestasi>() {
            @Override
            public void onSuccess(Prestasi model) {
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
