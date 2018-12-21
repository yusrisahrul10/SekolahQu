package com.dscunikom.android.sekolahqu.detail.acara;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class DetailAcaraPresenter extends BasePresenter<DetailAcaraView> {

    public DetailAcaraPresenter(DetailAcaraView view) {
        super.attachView(view);
    }

    void getDetailAcara(String id_acara){
        view.showLoading();
        addSubscribe(apiStores.getDetailAcara(id_acara), new NetworkCallback<AcaraModel>() {
            @Override
            public void onSuccess(AcaraModel model) {
                view.showDetailAcara(model);
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
