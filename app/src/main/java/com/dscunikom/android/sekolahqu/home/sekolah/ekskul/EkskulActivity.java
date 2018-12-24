package com.dscunikom.android.sekolahqu.home.sekolah.ekskul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.EkskulAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;
import com.dscunikom.android.sekolahqu.model.ekskul.EkskulResponse;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

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
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getEkskulFasilitas("22");
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToEkskulActivity(list.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdToEkskulActivity(list.get(position), activity);
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
        startActivity(intent);
    }
}
