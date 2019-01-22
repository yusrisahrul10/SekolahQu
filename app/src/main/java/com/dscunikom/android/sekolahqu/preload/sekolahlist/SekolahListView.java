package com.dscunikom.android.sekolahqu.preload.sekolahlist;


import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.sekolah.SekolahResponse;

public interface SekolahListView {
    void showLoading();
    void hideLoading();
    void showSekolahListSuccess(SekolahResponse model);
    void showSekolahListFailed(String message);
    void moveToActivity(Intent intent);
}
