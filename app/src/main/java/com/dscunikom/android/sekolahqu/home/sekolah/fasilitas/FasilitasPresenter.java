package com.dscunikom.android.sekolahqu.home.sekolah.fasilitas;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
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

//    void getIdtoDetailFasilitas(Fasilitas fasilitas, Activity activity) {
//        Intent intent = new Intent(activity, D)
//    }
}
