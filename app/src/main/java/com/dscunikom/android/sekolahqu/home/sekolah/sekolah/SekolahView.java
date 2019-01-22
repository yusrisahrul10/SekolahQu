package com.dscunikom.android.sekolahqu.home.sekolah.sekolah;

import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiLimit;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;

public interface SekolahView {
    void showImagePrestasi(PrestasiLimit model);
    void showImagePrestasiFailed(String message);
    void showImageLogoSekolah(Sekolah model);
    void showImageLogoSekolahFailed(String message);
    void moveToActivity(Intent intent);
}
