package com.dscunikom.android.sekolahqu.home.event;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.acara.DetailAcaraActivity;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
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
                view.hideLoading();
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
    void getIdAcara(AcaraModel model , Activity activity){
        Intent intent = new Intent(activity,DetailAcaraActivity.class);
        intent.putExtra("id_acara",model.getIdAcara());
        view.moveToActivity(intent);
    }
}
