package com.dscunikom.android.sekolahqu.home.awards;

import com.dscunikom.android.sekolahqu.model.PrestasiResponse;

public interface PrestasiView {
    void showLoading();
    void hideLoading();
    void showListPrestasi(PrestasiResponse model);
    void showListPrestasiFailed(String message);
}
