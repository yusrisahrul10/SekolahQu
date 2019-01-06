package com.dscunikom.android.sekolahqu.detail.fasilitas;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.GambarResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class DetailFasilitasPresenter extends BasePresenter<DetailFasilitasView> {

    public DetailFasilitasPresenter(DetailFasilitasView view) {
        super.attachView(view);
    }

    void getDetailFasilitas(String id_fasilitas){
        view.showLoading();
        addSubscribe(apiStores.getGambarFasilitas(id_fasilitas), new NetworkCallback<GambarResponse>() {
            @Override
            public void onSuccess(GambarResponse model) {
                view.showDetailFasilitas(model);
                view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                view.showDetailFasilitasFailed(message);
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
