package com.dscunikom.android.sekolahqu.home.awards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.adapter.PrestasiAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.detail.DetailPrestasiActivity;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.SpesifikSekolah;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

import java.util.HashMap;
import java.util.List;

public class AwardsFragment extends MvpFragment<PrestasiPresenter> implements PrestasiView {
//    @BindView(R.id.rv_awards)
    RecyclerView recyclerView;
    private List<SpesifikSekolah> mList;
    private List<SpesifikSekolah> mListFirst;

    SessionManager sessionManager;
    String id;
    @BindView(R.id.image_fragment_prestasi)
    ImageView imgAwardsNew;

//    @BindView(R.id.text_fragment_prestasi)
    TextView tvAwardsNew;

    @Override
    protected PrestasiPresenter createPresenter() {
        return new PrestasiPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_awards, container, false);
        tvAwardsNew = rootView.findViewById(R.id.text_fragment_prestasi);
        //init presenter disini , entah kenapa variable presenter selalu null
        presenter = createPresenter();
        //jadi harus di panggil terus menerus bapukkkk

        recyclerView = rootView.findViewById(R.id.rv_awards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        cvAwards = rootView.findViewById(R.id.cv_awards);
//        cvAwards.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), DetailPrestasiActivity.class);
//                startActivity(intent);
//            }
//        });

        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
        id = "22";
        presenter.getData("22");
//        Log.e("Message", "Message Prestasi : "+String.valueOf(presenter));
//        tvAwardsNew.setText("Helsan");

        return rootView;
    }

    private RecyclerView.OnItemTouchListener selectItemOnRecyclerView() {
        return null;
    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListPrestasi(PrestasiResponse model) {
        this.mList = model.getSpesifikSekolah();
        this.mList.remove(mList.get(0));
//        this.mListFirst = model.getFirstData();
//        this.mList.set(mList)
        recyclerView.setAdapter(new PrestasiAdapter(mList,R.layout.item_content,this.getActivity()));
//        recyclerView.setVisibility(View.INVISIBLE);
//        this.mList.add(mList.get(0));
//       this.mListFirst.addAll(model.getFirstData());
        tvAwardsNew.setText(model.getFirstData().get(0).getNamaPrestasi());
        Log.e("Message", "Message Prestasi Index 0: "+String.valueOf(model.getFirstData().get(0).getNamaPrestasi()));

    }

    @Override
    public void showListPrestasiFailed(String message) {

    }
}
