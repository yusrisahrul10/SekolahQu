package com.dscunikom.android.sekolahqu.home.awards;

import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;

public interface PrestasiView {
    void showLoading();
    void hideLoading();
    void showListPrestasi(PrestasiResponse model);
    void showListPrestasiFailed(String message);
    void moveToActivity(Intent intent);

}
