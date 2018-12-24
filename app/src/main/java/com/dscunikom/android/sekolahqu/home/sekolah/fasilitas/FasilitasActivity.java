package com.dscunikom.android.sekolahqu.home.sekolah.fasilitas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.FasilitasAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.fasilitas.Fasilitas;
import com.dscunikom.android.sekolahqu.model.fasilitas.FasilitasResponse;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

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
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getListFasilitas("22");
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdtoDetailFasilitas(list.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdtoDetailFasilitas(list.get(position), activity);
            }
        });
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
            startActivity(intent);
    }
}
