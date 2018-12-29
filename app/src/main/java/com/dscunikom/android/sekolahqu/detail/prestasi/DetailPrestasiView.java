package com.dscunikom.android.sekolahqu.detail.prestasi;

import com.dscunikom.android.sekolahqu.model.prestasi.SpesifikSekolah;

public interface DetailPrestasiView {
    void showLoading();
    void hideLoading();
    void showDetailPrestasi(SpesifikSekolah model);
    void addFavoritePrestasi(SpesifikSekolah model);
    void showDetatailFailed(String message);
}
