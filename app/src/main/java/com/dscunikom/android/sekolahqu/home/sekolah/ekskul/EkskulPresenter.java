package com.dscunikom.android.sekolahqu.home.sekolah.ekskul;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.ekskul.DetailEkskulActivity;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;
import com.dscunikom.android.sekolahqu.model.ekskul.EkskulResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class EkskulPresenter extends BasePresenter<EkskulView> {
    public EkskulPresenter(EkskulView view) {
        super.attachView(view);
    }

    void getEkskulFasilitas(String id) {
        view.showLoading();
        addSubscribe(apiStores.getEkskulSekolah(id), new NetworkCallback<EkskulResponse>() {
            @Override
            public void onSuccess(EkskulResponse model) {
                view.showListEkskul(model);
            }

            @Override
            public void onFailure(String message) {
                view.showListEkskulFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }
    void getIdToEkskulActivity(Ekskul ekskul , Activity activity){
        Intent intent = new Intent(activity, DetailEkskulActivity.class);
        intent.putExtra("id_ekskul", ekskul.getIdEkskul());
        view.moveToActivity(intent);
    }
}
