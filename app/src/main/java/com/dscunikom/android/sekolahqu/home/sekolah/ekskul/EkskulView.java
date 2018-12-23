package com.dscunikom.android.sekolahqu.home.sekolah.ekskul;

import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.ekskul.EkskulResponse;

public interface EkskulView {
    void showLoading();
    void hideLoading();
    void showListEkskul(EkskulResponse model);
    void showListEkskulFailed(String message);
    void moveToActivity(Intent intent);
}
