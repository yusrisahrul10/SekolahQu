package com.dscunikom.android.sekolahqu.home.sekolah.kalender;

import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.model.kalender.KalenderResponse;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;

public class KalenderPresenter extends BasePresenter<KalenderView> {
    public KalenderPresenter(KalenderView view) {
        super.attachView(view);
    }
    void getJanuary(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListJanuary(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getFebuary(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListFebuary(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getMaret(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListMaret(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getApril(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListApril(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getMei(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListMei(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getjuni(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListJuni(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getjuli(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListJuli(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getAgustus(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListAgustus(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getSeptember(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListSeptember(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getOktober(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListOktober(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
    void getNovember(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListNovember(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    void getDesember(String id_sekolah){
        view.showLoading();
        addSubscribe(apiStores.getKalenderAkademik(id_sekolah), new NetworkCallback<KalenderResponse>() {
            @Override
            public void onSuccess(KalenderResponse model) {
                view.showListDesember(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }




}
