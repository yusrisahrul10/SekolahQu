package com.dscunikom.android.sekolahqu.home.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.adapter.BeritaAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.detail.DetailBeritaActivity;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.berita.SpesifikSekolah;

import java.util.List;

public class NewsFragment extends MvpFragment<BeritaPresenter> implements BeritaView {
    private List<SpesifikSekolah> mList;
    TextView tvBeritaNews;
    ImageView imgBeritaNews;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_news, container, false);
        tvBeritaNews = rootView.findViewById(R.id.text_fragment_berita);
        imgBeritaNews = rootView.findViewById(R.id.image_fragment_berita);
        presenter = createPresenter();
        recyclerView = rootView.findViewById(R.id.rv_berita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        presenter.getDataBerita("22");
        return rootView;

    }

    private RecyclerView.OnItemTouchListener selectItemOnRecyclerView() {
        return null;
    }


    @Override
    protected BeritaPresenter createPresenter() {
        return new BeritaPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListBerita(BeritaResponse model) {
        this.mList = model.getSpesifikSekolah();
        this.mList.remove(mList.get(0));
        tvBeritaNews.setText(model.getmFirstData().get(0).getNamaBerita());
        Glide.with(this.getActivity())
                .load("http://sekolahqu.dscunikom.com/uploads/berita/"+model.getmFirstData().get(0).getImage())
                .into(imgBeritaNews);

        recyclerView.setAdapter(new BeritaAdapter(mList,R.layout.item_content,this.getActivity()));
    }

    @Override
    public void showListBeritaFailed(String message) {

    }
}
