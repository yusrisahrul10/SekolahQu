package com.dscunikom.android.sekolahqu.home.sekolah.sekolah;

import android.content.Intent;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiLimit;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;

public interface SekolahView {
    void showImagePrestasi(PrestasiLimit model);
    void showImagePrestasiFailed(String message);
    void moveToActivity(Intent intent);
}
