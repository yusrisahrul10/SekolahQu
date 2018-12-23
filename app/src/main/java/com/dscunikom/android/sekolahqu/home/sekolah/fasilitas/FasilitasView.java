package com.dscunikom.android.sekolahqu.home.sekolah.fasilitas;

import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.fasilitas.FasilitasResponse;

public interface FasilitasView {
    void showLoading();
    void hideLoading();
    void showListFasilitas(FasilitasResponse model);
    void showListFasilitasFailed(String message);
    void moveToActivity(Intent intent);
}
