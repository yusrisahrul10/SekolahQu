package com.dscunikom.android.sekolahqu.home.sekolah.visimisi;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class VisiMisiPresenter extends BasePresenter<VisiMisiView> {
//presenter nge-extends ke view interface

    public VisiMisiPresenter(VisiMisiView view) {
        super.attachView(view);
    }

    void getData(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getDetailSekolah(id_sekolah), new NetworkCallback<Sekolah>() {

            @Override
            public void onSuccess(Sekolah model) {
                view.showDetailSekolah(model);
            }

            @Override
            public void onFailure(String message) {
                view.showSekolahListFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
}
