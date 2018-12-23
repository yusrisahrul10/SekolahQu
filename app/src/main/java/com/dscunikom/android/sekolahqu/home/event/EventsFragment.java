package com.dscunikom.android.sekolahqu.home.event;

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
import com.dscunikom.android.sekolahqu.adapter.AcaraAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.List;

public class EventsFragment extends MvpFragment<AcaraPresenter> implements AcaraView {
    RecyclerView recyclerView;
    private List<AcaraModel> mList;
    ImageView imgAcaraNews;
    TextView tvAcaraNews;
    SessionManager sessionManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_events, container, false);
        presenter = createPresenter();
        recyclerView = rootView.findViewById(R.id.rv_events);
        tvAcaraNews = rootView.findViewById(R.id.text_fragment_acara);
        imgAcaraNews = rootView.findViewById(R.id.image_fragment_acara);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
//        FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);
        presenter.getDataAcara(id_sekolah);
        return rootView;

    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdAcara(mList.get(position), activity);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdAcara(mList.get(position), activity);
            }
        });
    }


    @Override
    protected AcaraPresenter createPresenter() {
        return new AcaraPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListAcara(AcaraResponse model) {
        this.mList = model.getSpesifikSekolah();
        this.mList.remove(mList.get(0));
        recyclerView.setAdapter(new AcaraAdapter(mList,R.layout.item_content,this.getActivity()));
        Glide.with(this.getActivity())
                .load("http://sekolahqu.dscunikom.com/uploads/acara/"+model.getmFirstData().get(0).getImage())
                .into(imgAcaraNews);
        tvAcaraNews.setText(model.getmFirstData().get(0).getNamaAcara());
    }

    @Override
    public void showListAcaraFailed(String message) {

    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
