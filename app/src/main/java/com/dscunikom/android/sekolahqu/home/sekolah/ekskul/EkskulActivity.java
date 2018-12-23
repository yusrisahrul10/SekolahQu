package com.dscunikom.android.sekolahqu.home.sekolah.ekskul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.EkskulAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;
import com.dscunikom.android.sekolahqu.model.ekskul.EkskulResponse;

import java.util.List;

public class EkskulActivity extends MvpActivity<EkskulPresenter> implements EkskulView {

    private List<Ekskul> list;
    @BindView(R.id.rv_ekskul)
    RecyclerView recyclerView;

    @Override
    protected EkskulPresenter createPresenter() {
        return new EkskulPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekskul);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getEkskulFasilitas("22");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListEkskul(EkskulResponse model) {
        this.list = model.getSpesifikSekolah();
        recyclerView.setAdapter(new EkskulAdapter(list, R.layout.item_ekskul_fasilitas, getApplicationContext()));
    }

    @Override
    public void showListEkskulFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToActivity(Intent intent) {

    }
}
