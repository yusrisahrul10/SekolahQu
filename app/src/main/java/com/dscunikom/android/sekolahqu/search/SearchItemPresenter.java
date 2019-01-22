package com.dscunikom.android.sekolahqu.search;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.dscunikom.android.sekolahqu.adapter.BeritaAdapter;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.detail.acara.DetailAcaraActivity;
import com.dscunikom.android.sekolahqu.detail.berita.DetailBeritaActivity;
import com.dscunikom.android.sekolahqu.detail.prestasi.DetailPrestasiActivity;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class SearchItemPresenter extends BasePresenter<SearchItemView> {
    public SearchItemPresenter(SearchItemView view) {
        super.attachView(view);
    }

    void getDataBerita(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getListBerita(id_sekolah), new NetworkCallback<BeritaResponse>() {
            @Override
            public void onSuccess(BeritaResponse model) {
                view.showListBerita(model);
            }

            @Override
            public void onFailure(String message) {
                view.showListBeritaFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void getDataAcara(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getListAcara(id_sekolah), new NetworkCallback<AcaraResponse>() {
            @Override
            public void onSuccess(AcaraResponse model) {
                view.showListAcara(model);
            }

            @Override
            public void onFailure(String message) {
                view.showListAcaraFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void getDataPrestasi(String id){
        view.showLoading();
        addSubscribe(apiStores.getListPrestasi(id), new NetworkCallback<PrestasiResponse>() {
            @Override
            public void onSuccess(PrestasiResponse model) {
                view.showListPrestasi(model);
                Log.e("Helsan ","Testing "+String.valueOf(model.getJumlahData()));
            }

            @Override
            public void onFailure(String message) {
                view.showListPrestasiFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }
//
//    void getSearchBerita(List<BeritaModel> datas, String keyword) {
//
//        List<BeritaModel> filteredList = new ArrayList<>();
//        for (BeritaModel s : datas) {
//            if (s.getNamaBerita().toLowerCase().contains(keyword)) {
//                filteredList.add(s);
//            }
//        }
//        RecyclerView.Adapter fAdapter = new BeritaAdapter()
//    }

    void getIdBerita(BeritaModel model, Activity activity){
        Intent intent = new Intent(activity,DetailBeritaActivity.class);
        intent.putExtra("id_berita",model.getIdBerita());
        view.moveToActivity(intent);
    }

    void getIdAcara(AcaraModel model , Activity activity){
        Intent intent = new Intent(activity,DetailAcaraActivity.class);
        intent.putExtra("id_acara",model.getIdAcara());
        view.moveToActivity(intent);
    }

    void getIdToPrestasi(Prestasi prestasi, Activity activity) {
        Intent intent = new Intent(activity, DetailPrestasiActivity.class);
        intent.putExtra("id_prestasi", prestasi.getIdPrestasi());
        view.moveToActivity(intent);
    }


}
