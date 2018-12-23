package com.dscunikom.android.sekolahqu.home.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

public class NewsFragment extends MvpFragment<BeritaPresenter> implements BeritaView {
    private List<BeritaModel> mList;
    TextView tvBeritaNews;
    ImageView imgBeritaNews;
    RecyclerView recyclerView;
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_news, container, false);
        tvBeritaNews = rootView.findViewById(R.id.text_fragment_berita);
        imgBeritaNews = rootView.findViewById(R.id.image_fragment_berita);
        presenter = createPresenter();
        recyclerView = rootView.findViewById(R.id.rv_berita);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
//        FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);
        presenter.getDataBerita(id_sekolah);
        return rootView;

    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdBerita(mList.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdBerita(mList.get(position), activity);
            }
        });
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

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
