package com.dscunikom.android.sekolahqu.detail.fasilitas;

import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.GambarResponse;

public interface DetailFasilitasView {
    void showLoading();
    void hideLoading();
    void showDetailFasilitas(GambarResponse model);
    void showDetatailFailed(String message);
}
