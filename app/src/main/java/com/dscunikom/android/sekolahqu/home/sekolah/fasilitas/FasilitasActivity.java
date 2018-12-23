package com.dscunikom.android.sekolahqu.home.sekolah.fasilitas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.FasilitasAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.fasilitas.Fasilitas;
import com.dscunikom.android.sekolahqu.model.fasilitas.FasilitasResponse;

import java.util.List;

public class FasilitasActivity extends MvpActivity<FasilitasPresenter> implements FasilitasView {
    private List<Fasilitas> list;
    @BindView(R.id.rv_fasilitas)
    RecyclerView recyclerView;

    @Override
    protected FasilitasPresenter createPresenter() {
        return new FasilitasPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasilitas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getListFasilitas("22");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListFasilitas(FasilitasResponse model) {
        this.list = model.getResult();
        recyclerView.setAdapter(new FasilitasAdapter(list, R.layout.item_ekskul_fasilitas, getApplicationContext()));
    }

    @Override
    public void showListFasilitasFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToActivity(Intent intent) {

    }
}
