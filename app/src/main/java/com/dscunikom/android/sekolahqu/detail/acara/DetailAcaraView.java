package com.dscunikom.android.sekolahqu.detail.acara;

import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;

public interface DetailAcaraView {
    void showLoading();
    void hideLoading();
    void showDetailAcara(AcaraModel model);
    void addFavoriteAcara(AcaraModel model);
    void showDetatailFailed(String message);

}
