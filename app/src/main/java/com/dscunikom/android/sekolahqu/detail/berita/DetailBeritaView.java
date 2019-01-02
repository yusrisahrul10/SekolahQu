package com.dscunikom.android.sekolahqu.detail.berita;

import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;

public interface DetailBeritaView {
    void showLoading();
    void hideLoading();
    void showDetailBerita(BeritaModel model);
    void addFavoriteBerita(BeritaModel model);
    void showDetatailFailed(String message);
}
