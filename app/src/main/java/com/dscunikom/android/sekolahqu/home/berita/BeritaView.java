package com.dscunikom.android.sekolahqu.home.berita;

import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;

public interface BeritaView {
    void showLoading();
    void hideLoading();
    void showListBerita(BeritaResponse model);
    void showListBeritaFailed(String message);
    void moveToActivity(Intent intent);
}
