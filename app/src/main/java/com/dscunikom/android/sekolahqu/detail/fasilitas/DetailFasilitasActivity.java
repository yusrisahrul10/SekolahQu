package com.dscunikom.android.sekolahqu.detail.fasilitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.DetailFasilitasAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.Gambar;
import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.GambarResponse;

import java.util.List;

public class DetailFasilitasActivity extends MvpActivity<DetailFasilitasPresenter> implements DetailFasilitasView {
    RecyclerView recyclerView;
    String id_fasilitas;
    private List<Gambar> mList;

    @Override
    protected DetailFasilitasPresenter createPresenter() {
        return new DetailFasilitasPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fasilitas);
        id_fasilitas = getIntent().getStringExtra("id_fasilitas");
        recyclerView = findViewById(R.id.rv_detail_fasilitas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.getDetailFasilitas(id_fasilitas);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDetailFasilitas(GambarResponse model) {
        this.mList = model.getGambar();
        recyclerView.setAdapter(new DetailFasilitasAdapter(mList,R.layout.item_detail_fasilitas,this));

    }

    @Override
    public void showDetatailFailed(String message) {

    }
}
