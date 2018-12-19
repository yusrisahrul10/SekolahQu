package com.dscunikom.android.sekolahqu.home.sekolah.visimisi;

import com.dscunikom.android.sekolahqu.model.Sekolah;

public interface VisiMisiView {
    void showLoading();
    void hideLoading();
    void showDetailSekolah(Sekolah model);
    void showSekolahListFailed(String message);

}
