package com.dscunikom.android.sekolahqu.home.sekolah.fasilitas;

import android.app.Activity;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.fasilitas.DetailFasilitasActivity;
import com.dscunikom.android.sekolahqu.model.fasilitas.Fasilitas;
import com.dscunikom.android.sekolahqu.model.fasilitas.FasilitasResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class FasilitasPresenter extends BasePresenter<FasilitasView> {
    public FasilitasPresenter(FasilitasView view) {
        super.attachView(view);
    }

    void getListFasilitas(String id) {
        view.showLoading();
        addSubscribe(apiStores.getFasilitasSekolah(id), new NetworkCallback<FasilitasResponse>() {
            @Override
            public void onSuccess(FasilitasResponse model) {
                view.showListFasilitas(model);
                view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                view.showListFasilitasFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void getIdtoDetailFasilitas(Fasilitas fasilitas, Activity activity) {
        Intent intent = new Intent(activity, DetailFasilitasActivity.class);
        intent.putExtra("id_fasilitas", fasilitas.getIdFasilitas());
        view.moveToActivity(intent);
    }
}
