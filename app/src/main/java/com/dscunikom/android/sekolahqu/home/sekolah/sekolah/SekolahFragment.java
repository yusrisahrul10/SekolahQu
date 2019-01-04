package com.dscunikom.android.sekolahqu.home.sekolah.sekolah;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dscunikom.android.sekolahqu.*;
import com.dscunikom.android.sekolahqu.adapter.PrestasiHomeAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.home.sekolah.ekskul.EkskulActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.fasilitas.FasilitasActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.kalender.KalenderActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.visimisi.VisiMisiActivity;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiLimit;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SekolahFragment extends MvpFragment<SekolahPresenter> implements SekolahView {


    public SekolahFragment() {

    }

    RecyclerView rvPrestasi;

    private List<Prestasi> mList;

    SessionManager sessionManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_sekolah, container, false);

        presenter = createPresenter();

        CardView btnVisiMisi = rootView.findViewById(R.id.btn_visi_misi);
        CardView btnFasilitas = rootView.findViewById(R.id.btn_fasilitas);
        CardView btnEkskul = rootView.findViewById(R.id.btn_ekskul);
        CardView btnKalender = rootView.findViewById(R.id.btn_kalender);
        rvPrestasi = rootView.findViewById(R.id.rv_prestasi_home);

        rvPrestasi.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPrestasi.addOnItemTouchListener(selectItemOnRecyclerView());
        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
        presenter.getImagePrestasi(id_sekolah);


        btnVisiMisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VisiMisiActivity.class);
                startActivity(intent);
            }
        });

        btnFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FasilitasActivity.class);
                startActivity(intent);
            }
        });

        btnEkskul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EkskulActivity.class);
                startActivity(intent);
            }
        });

        btnKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KalenderActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvPrestasi, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToPrestasi(mList.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdToPrestasi(mList.get(position), activity);
            }
        });
    }

    @Override
    protected SekolahPresenter createPresenter() {
        return new SekolahPresenter(this);
    }

    @Override
    public void showImagePrestasi(PrestasiLimit model) {
        this.mList = model.getResult();
        rvPrestasi.setAdapter(new PrestasiHomeAdapter(mList,R.layout.item_prestasi,this.getActivity()));
    }

    @Override
    public void showImagePrestasiFailed(String message) {

    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
