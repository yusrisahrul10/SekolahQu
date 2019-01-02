package com.dscunikom.android.sekolahqu.home.sekolah.kalender;

import com.dscunikom.android.sekolahqu.model.kalender.KalenderResponse;

public interface KalenderView {
    void showLoading();
    void hideLoading();
    void showListJanuary(KalenderResponse model);
    void showListFebuary(KalenderResponse model);
    void showListMaret(KalenderResponse model);
    void showListApril(KalenderResponse model);
    void showListMei(KalenderResponse model);
    void showListJuni(KalenderResponse model);
    void showListJuli(KalenderResponse model);
    void showListAgustus(KalenderResponse model);
    void showListSeptember(KalenderResponse model);
    void showListOktober(KalenderResponse model);
    void showListNovember(KalenderResponse model);
    void showListDesember(KalenderResponse model);

    void showListFasilitasFailed(String message);
}
