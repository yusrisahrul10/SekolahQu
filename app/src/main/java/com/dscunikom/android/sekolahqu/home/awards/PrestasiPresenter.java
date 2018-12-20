package com.dscunikom.android.sekolahqu.home.awards;

import android.util.Log;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.PrestasiResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class PrestasiPresenter extends BasePresenter<PrestasiView> {

    public PrestasiPresenter(PrestasiView view) {
        super.attachView(view);
    }
    void getData(String id){
        view.showLoading();
       addSubscribe(apiStores.getListPrestasi(id), new NetworkCallback<PrestasiResponse>() {
           @Override
           public void onSuccess(PrestasiResponse model) {
               view.showListPrestasi(model);
               Log.e("Helsan ","Testing "+String.valueOf(model.getJumlahData()));
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
