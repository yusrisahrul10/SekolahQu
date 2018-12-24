package com.dscunikom.android.sekolahqu.detail.ekskul;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class DetailEkskulPresenter extends BasePresenter<DetailEkskulView> {
    public DetailEkskulPresenter(DetailEkskulView view) {
        super.attachView(view);
    }
    void getDetailEkskul(String id_ekskul){
        view.showLoading();
        addSubscribe(apiStores.getDetailEkskul(id_ekskul), new NetworkCallback<Ekskul>() {
            @Override
            public void onSuccess(Ekskul model) {
                view.showDetailEkskul(model);
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
}
