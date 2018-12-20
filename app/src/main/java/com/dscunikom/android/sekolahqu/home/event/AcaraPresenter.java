package com.dscunikom.android.sekolahqu.home.event;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class AcaraPresenter extends BasePresenter<AcaraView> {
    public AcaraPresenter(AcaraView view) {
        super.attachView(view);
    }
    void getDataAcara(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getListAcara(id_sekolah), new NetworkCallback<AcaraResponse>() {
            @Override
            public void onSuccess(AcaraResponse model) {
                view.showListAcara(model);
            }

            @Override
            public void onFailure(String message) {
                view.showListAcaraFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
