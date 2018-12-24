package com.dscunikom.android.sekolahqu.detail.ekskul;

import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;

public interface DetailEkskulView {
    void showLoading();
    void hideLoading();
    void showDetailEkskul(Ekskul model);
    void showDetatailFailed(String message);
}
