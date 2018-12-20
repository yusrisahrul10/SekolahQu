package com.dscunikom.android.sekolahqu.home.awards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.adapter.PrestasiAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.SpesifikSekolah;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

import java.util.HashMap;
import java.util.List;

public class AwardsFragment extends MvpFragment<PrestasiPresenter> implements PrestasiView {

    RecyclerView recyclerView;
    private List<SpesifikSekolah> mList;

    SessionManager sessionManager;
    String id;
//    @BindView(R.id.image_fragment_prestasi)
    ImageView imgAwardsNew;

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
        imgAwardsNew = rootView.findViewById(R.id.image_fragment_prestasi);

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
        presenter.getDataPrestasi("22");
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

        recyclerView.setAdapter(new PrestasiAdapter(mList,R.layout.item_content,this.getActivity()));
        Glide.with(this.getActivity())
                .load("http://sekolahqu.dscunikom.com/uploads/prestasi/"+model.getFirstData().get(0).getImage())
                .into(imgAwardsNew);
        tvAwardsNew.setText(model.getFirstData().get(0).getNamaPrestasi());
        Log.e("Message", "Message Prestasi Index 0: "+String.valueOf(model.getFirstData().get(0).getNamaPrestasi()));

    }

    @Override
    public void showListPrestasiFailed(String message) {

    }
}
