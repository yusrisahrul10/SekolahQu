package com.dscunikom.android.sekolahqu.home.sekolah.sekolah;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.*;
import com.dscunikom.android.sekolahqu.adapter.PrestasiHomeAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.home.sekolah.ekskul.EkskulActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.fasilitas.FasilitasActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.kalender.KalenderActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.visimisi.VisiMisiActivity;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiLimit;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;
import com.google.firebase.analytics.FirebaseAnalytics;

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
    ImageView ivPrestasi;
    SwipeRefreshLayout swipeRefresh;
    ImageView ivLogoSekolah;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_sekolah, container, false);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        presenter = createPresenter();

        CardView btnVisiMisi = rootView.findViewById(R.id.btn_visi_misi);
        CardView btnFasilitas = rootView.findViewById(R.id.btn_fasilitas);
        CardView btnEkskul = rootView.findViewById(R.id.btn_ekskul);
        CardView btnKalender = rootView.findViewById(R.id.btn_kalender);
        rvPrestasi = rootView.findViewById(R.id.rv_prestasi_home);
        ivPrestasi = rootView.findViewById(R.id.iv_prestasi_failed);
        swipeRefresh = rootView.findViewById(R.id.swipe_sekolah_home);
        ivLogoSekolah = rootView.findViewById(R.id.iv_logo_sekolah);

        mFirebaseAnalytics.setCurrentScreen(this.getActivity(), getActivity().getClass().getSimpleName(), getActivity().getClass().getSimpleName());

        rvPrestasi.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPrestasi.addOnItemTouchListener(selectItemOnRecyclerView());
        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
        presenter.getImagePrestasi(id_sekolah);
        presenter.getLogoSekolah(id_sekolah);
        swipeRefresh.setOnRefreshListener(() -> {
            presenter.getImagePrestasi(id_sekolah);
            presenter.getLogoSekolah(id_sekolah);
        });

        btnVisiMisi.setOnClickListener(view -> {

            Intent intent = new Intent(getActivity(), VisiMisiActivity.class);
            startActivity(intent);
            String name = "btnVisiMisi";
            Bundle params = new Bundle();
            params.putString("BtnVisiMisi", name);
            mFirebaseAnalytics.logEvent("tombol_visi_misi",params);
            Log.e("Firebase Analytics ","Error : ");
    //            params.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"btn_visi_misi");

//            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,params);
        });

        btnFasilitas.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FasilitasActivity.class);
            startActivity(intent);
            String name = "btnFasilitas";
            Bundle params = new Bundle();
            params.putString("BtnVisiFasilitas", name);
            mFirebaseAnalytics.logEvent("tombol_fasilitas",params);
            Log.e("Firebase Analytics ","Error : ");
            params.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"btn_fasilitas");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,params);

        });

        btnEkskul.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EkskulActivity.class);
            startActivity(intent);
            String name = "btnEkskul";
            Bundle params = new Bundle();
            params.putString("BtnVisiEkskul", name);
            mFirebaseAnalytics.logEvent("tombol_ekskul",params);
            Log.e("Firebase Analytics ","Error : ");
            params.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"btn_ekskul");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,params);
        });

        btnKalender.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), KalenderActivity.class);
            startActivity(intent);
            String name = "btnKalender";
            Bundle params = new Bundle();
            params.putString("BtnVisiFasilitas", name);
            mFirebaseAnalytics.logEvent("tombol_kalender",params);
            Log.e("Firebase Analytics ","Error : ");
            params.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"btn_kalender");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,params);
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
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
        swipeRefresh.setRefreshing(false);
        this.mList = model.getResult();
        if (mList.size() == 0) {
            ivPrestasi.setImageResource(R.drawable.img_broken);
            ivPrestasi.setVisibility(View.VISIBLE);
            rvPrestasi.setVisibility(View.GONE);
        } else {
            rvPrestasi.setAdapter(new PrestasiHomeAdapter(mList,R.layout.item_prestasi,this.getActivity()));
            rvPrestasi.setVisibility(View.VISIBLE);
            ivPrestasi.setVisibility(View.GONE);
        }

    }

    @Override
    public void showImagePrestasiFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        ivPrestasi.setImageResource(R.drawable.img_broken);
        ivPrestasi.setVisibility(View.VISIBLE);
        rvPrestasi.setVisibility(View.GONE);
    }

    @Override
    public void showImageLogoSekolah(Sekolah model) {
        swipeRefresh.setRefreshing(false);
        Glide.with(this.getActivity())
                .load("http://sekolahqu.dscunikom.com/uploads/profile_sekolah/"+model.getLogoSekolah())
                .into(ivLogoSekolah);
    }

    @Override
    public void showImageLogoSekolahFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        ivLogoSekolah.setImageResource(R.drawable.img_broken);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
