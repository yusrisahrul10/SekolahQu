package com.dscunikom.android.sekolahqu.home.event;

import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;

public interface AcaraView {
    void showLoading();
    void hideLoading();
    void showListAcara(AcaraResponse model);
    void showListAcaraFailed(String message);
}
