package com.dscunikom.android.sekolahqu.home.sekolah.kalender;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.AdapterKalender;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.kalender.Kalender;
import com.dscunikom.android.sekolahqu.model.kalender.KalenderResponse;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

import java.util.HashMap;
import java.util.List;

public class KalenderActivity extends MvpActivity<KalenderPresenter> implements KalenderView {
    RecyclerView rvJanuary,rvFebuary,rvMaret,rvApril,rvMei,rvJuni,rvJuli,rvAgustus,rvSeptember,rvOktober,rvNopember,rvDesember;
    TextView tvDataKosongFeb,tvDataKosongJan,tvMar,tvApr,tvMei,tvJun,tvJul,tvAgus,tvSep,tvOkt,tvNov,tvDec;
    ImageView imgDataKosongFeb,imgDataKosongJan ,imgMaret,imgApril,imgMei,imgJun,imgJul,imgAgus,imgSep,imgOkt,imgNov,imgDec;
    private List<Kalender> mList;
    SessionManager sessionManager;
    @Override
    protected KalenderPresenter createPresenter() {
        return new KalenderPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);
        tvDataKosongFeb = findViewById(R.id.DataKosongFeb);
        imgDataKosongFeb = findViewById(R.id.imgDataKosongFeb);
        tvDataKosongJan = findViewById(R.id.DataKosongJan);
        imgDataKosongJan = findViewById(R.id.imgDataKosongJan);
        tvMar = findViewById(R.id.DataKosongMar);
        imgMaret = findViewById(R.id.imgDataKosongMar);
        tvApr = findViewById(R.id.DataKosongApr);
        imgApril = findViewById(R.id.imgDataKosongApr);
        tvMei = findViewById(R.id.DataKosongMei);
        imgMei = findViewById(R.id.imgDataKosongMei);
        tvJun = findViewById(R.id.DataKosongJun);
        imgJun = findViewById(R.id.imgDataKosongJun);
        tvJul = findViewById(R.id.DataKosongJul);
        imgJul = findViewById(R.id.imgDataKosongJul);
        tvAgus = findViewById(R.id.DataKosongAgus);
        imgAgus = findViewById(R.id.imgDataKosongAgus);
        tvSep = findViewById(R.id.DataKosongSep);
        imgSep = findViewById(R.id.imgDataKosongSep);
        tvOkt = findViewById(R.id.DataKosongOkt);
        imgOkt = findViewById(R.id.imgDataKosongOkt);
        tvNov = findViewById(R.id.DataKosongNov);
        imgNov = findViewById(R.id.imgDataKosongNov);
        tvDec = findViewById(R.id.DataKosongDec);
        imgDec = findViewById(R.id.imgDataKosongDec);

        rvJanuary = findViewById(R.id.januari);
        rvJanuary.setLayoutManager(new LinearLayoutManager(this));

        rvFebuary = findViewById(R.id.februari);
        rvFebuary.setLayoutManager(new LinearLayoutManager(this));

        rvMaret = findViewById(R.id.maret);
        rvMaret.setLayoutManager(new LinearLayoutManager(this));

        rvApril = findViewById(R.id.april);
        rvApril.setLayoutManager(new LinearLayoutManager(this));

        rvMei = findViewById(R.id.mei);
        rvMei.setLayoutManager(new LinearLayoutManager(this));

        rvJuni = findViewById(R.id.juni);
        rvJuni.setLayoutManager(new LinearLayoutManager(this));

        rvJuli = findViewById(R.id.juli);
        rvJuli.setLayoutManager(new LinearLayoutManager(this));

        rvAgustus = findViewById(R.id.agustus);
        rvAgustus.setLayoutManager(new LinearLayoutManager(this));

        rvSeptember = findViewById(R.id.september);
        rvSeptember.setLayoutManager(new LinearLayoutManager(this));

        rvOktober = findViewById(R.id.oktober);
        rvOktober.setLayoutManager(new LinearLayoutManager(this));

        rvNopember = findViewById(R.id.november);
        rvNopember.setLayoutManager(new LinearLayoutManager(this));

        rvDesember = findViewById(R.id.desember);
        rvDesember.setLayoutManager(new LinearLayoutManager(this));

        sessionManager = new SessionManager(this);
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initData(id_sekolah);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return true;
    }

    public void initData(String id_sekolah) {
        presenter.getJanuary(id_sekolah);
        presenter.getFebuary(id_sekolah);
        presenter.getMaret(id_sekolah);
        presenter.getApril(id_sekolah);
        presenter.getMei(id_sekolah);
        presenter.getjuni(id_sekolah);
        presenter.getjuli(id_sekolah);
        presenter.getAgustus(id_sekolah);
        presenter.getSeptember(id_sekolah);
        presenter.getOktober(id_sekolah);
        presenter.getNovember(id_sekolah);
        presenter.getDesember(id_sekolah);
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListJanuary(KalenderResponse model) {
        this.mList = model.getJanuary();
        if(mList.size() == 0){
            rvJanuary.setVisibility(View.GONE);
            tvDataKosongJan.setVisibility(View.VISIBLE);
            imgDataKosongJan.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvJanuary.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            rvJanuary.setVisibility(View.VISIBLE);
            tvDataKosongJan.setVisibility(View.GONE);
            imgDataKosongJan.setVisibility(View.GONE);
        }


    }

    @Override
    public void showListFebuary(KalenderResponse model) {

        this.mList = model.getFebuary();

        if(mList.size() == 0){
            rvFebuary.setVisibility(View.GONE);
            tvDataKosongFeb.setVisibility(View.VISIBLE);
            imgDataKosongFeb.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvFebuary.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            rvFebuary.setVisibility(View.VISIBLE);
            tvDataKosongFeb.setVisibility(View.GONE);
            imgDataKosongFeb.setVisibility(View.GONE);
        }


    }

    @Override
    public void showListMaret(KalenderResponse model) {
        this.mList = model.getMaret();

        if(mList.size() == 0){
            rvMaret.setVisibility(View.GONE);
            tvMar.setVisibility(View.VISIBLE);
            imgMaret.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvMaret.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            rvMaret.setVisibility(View.VISIBLE);
            tvMar.setVisibility(View.GONE);
            imgMaret.setVisibility(View.GONE);
        }

    }

    @Override
    public void showListApril(KalenderResponse model) {
        this.mList = model.getApril();
        if(mList.size() == 0){
            rvApril.setVisibility(View.GONE);
            tvApr.setVisibility(View.VISIBLE);
            imgApril.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvApril.setAdapter(adapterKalender);
            rvApril.setVisibility(View.VISIBLE);
            adapterKalender.notifyDataSetChanged();
            tvApr.setVisibility(View.GONE);
            imgApril.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListMei(KalenderResponse model) {
        this.mList = model.getMei();
        if(mList.size() == 0){
            tvMei.setVisibility(View.VISIBLE);
            imgMei.setVisibility(View.VISIBLE);
            rvMei.setVisibility(View.GONE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvMei.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            rvMei.setVisibility(View.VISIBLE);
            tvMei.setVisibility(View.GONE);
            imgMei.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListJuni(KalenderResponse model) {
        this.mList = model.getJuni();
        if(mList.size() == 0){
            rvJuni.setVisibility(View.GONE);

            tvJun.setVisibility(View.VISIBLE);
            imgJun.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvJuni.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            rvJuni.setVisibility(View.VISIBLE);
            tvJun.setVisibility(View.GONE);
            imgJun.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListJuli(KalenderResponse model) {
        this.mList = model.getJuli();
        if(mList.size() == 0){
            rvJuli.setVisibility(View.GONE);

            tvJul.setVisibility(View.VISIBLE);
            imgJul.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvJuli.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            rvJuli.setVisibility(View.VISIBLE);
            tvJul.setVisibility(View.GONE);
            imgJul.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListAgustus(KalenderResponse model) {
        this.mList = model.getAgustus();
        if(mList.size() == 0){
            rvAgustus.setVisibility(View.GONE);

            tvAgus.setVisibility(View.VISIBLE);
            imgAgus.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvAgustus.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            tvAgus.setVisibility(View.GONE);
            imgAgus.setVisibility(View.GONE);

            rvAgustus.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showListSeptember(KalenderResponse model) {
        this.mList = model.getSeptember();
        if(mList.size() == 0){
            rvSeptember.setVisibility(View.GONE);

            tvSep.setVisibility(View.VISIBLE);
            imgSep.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvSeptember.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            tvSep.setVisibility(View.GONE);

            rvSeptember.setVisibility(View.VISIBLE);
            imgSep.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListOktober(KalenderResponse model) {
        this.mList = model.getOktober();
        if(mList.size() == 0){
            rvOktober.setVisibility(View.GONE);

            tvOkt.setVisibility(View.VISIBLE);
            tvOkt.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvOktober.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            tvOkt.setVisibility(View.GONE);
            tvOkt.setVisibility(View.GONE);

            rvOktober.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showListNovember(KalenderResponse model) {
        this.mList = model.getNovember();
        if(mList.size() == 0){
            rvNopember.setVisibility(View.GONE);

            tvNov.setVisibility(View.VISIBLE);
            imgNov.setVisibility(View.VISIBLE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvNopember.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            tvNov.setVisibility(View.GONE);

            rvNopember.setVisibility(View.VISIBLE);
            imgNov.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListDesember(KalenderResponse model) {
        this.mList = model.getMei();
        if(mList.size() == 0){
            tvDec.setVisibility(View.VISIBLE);
            tvDec.setVisibility(View.VISIBLE);
            rvDesember.setVisibility(View.GONE);
        }else{
            AdapterKalender adapterKalender = new AdapterKalender(mList,R.layout.list_item_kalender,getApplicationContext());
            rvDesember.setAdapter(adapterKalender);
            adapterKalender.notifyDataSetChanged();
            tvDec.setVisibility(View.GONE);
            rvDesember.setVisibility(View.VISIBLE);
            tvDec.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListFasilitasFailed(String message) {

    }
}
