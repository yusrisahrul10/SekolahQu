package com.dscunikom.android.sekolahqu.detail.prestasi;

import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;

public interface DetailPrestasiView {
    void showLoading();
    void hideLoading();
    void showDetailPrestasi(Prestasi model);
    void addFavoritePrestasi(Prestasi model);
    void showDetatailFailed(String message);
}
