package com.dscunikom.android.sekolahqu.search;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;

import java.util.List;

public interface SearchItemView {
    void showLoading();
    void hideLoading();
    void showListBerita(BeritaResponse model);
    void showListBeritaFailed(String message);
    void showListAcara(AcaraResponse model);
    void showListAcaraFailed(String message);
    void showListPrestasi(PrestasiResponse model);
    void showListPrestasiFailed(String message);
    void moveToActivity(Intent intent);
//    void searchBerita(RecyclerView.Adapter adapter, List<BeritaModel> list);
}
