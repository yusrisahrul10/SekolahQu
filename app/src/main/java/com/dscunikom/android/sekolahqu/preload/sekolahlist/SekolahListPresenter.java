package com.dscunikom.android.sekolahqu.preload.sekolahlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.dscunikom.android.sekolahqu.base.ui.BasePresenter;
import com.dscunikom.android.sekolahqu.network.NetworkCallback;
import com.dscunikom.android.sekolahqu.main.MainActivity;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.model.sekolah.SekolahResponse;
import com.dscunikom.android.sekolahqu.preload.NotifActivity;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

public class SekolahListPresenter extends BasePresenter<SekolahListView> {
    SessionManager sessionManager;
    Context context;
    SekolahListPresenter(SekolahListView view){
        super.attachView(view);
    }

    void loadData(){
        view.showLoading();
        addSubscribe(apiStores.getListSekolah(), new NetworkCallback<SekolahResponse>() {
            @Override
            public void onSuccess(SekolahResponse model) {
                view.showSekolahListSuccess(model);
                view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                view.showSekolahListFailed(message);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void getSekolahToNotif(Sekolah sekolah, Activity activity) {
        Intent intent = new Intent(activity, NotifActivity.class);
        intent.putExtra("id_sekolah", sekolah.getIdSekolah());
        view.moveToActivity(intent);
    }

    void getSekolahToHome(Sekolah sekolah, Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("id_sekolah", sekolah.getIdSekolah());
        view.moveToActivity(intent);
    }
}
