package com.dscunikom.android.sekolahqu.home.prestasi;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.prestasi.DetailPrestasiActivity;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class PrestasiPresenter extends BasePresenter<PrestasiView> {

    public PrestasiPresenter(PrestasiView view) {
        super.attachView(view);
    }
    void getDataPrestasi(String id){
        view.showLoading();
       addSubscribe(apiStores.getListPrestasi(id), new NetworkCallback<PrestasiResponse>() {
           @Override
           public void onSuccess(PrestasiResponse model) {
               view.showListPrestasi(model);
               view.hideLoading();
           }

           @Override
           public void onFailure(String message) {
                view.showListPrestasiFailed(message);
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
