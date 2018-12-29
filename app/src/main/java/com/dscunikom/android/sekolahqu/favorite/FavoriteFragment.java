package com.dscunikom.android.sekolahqu.favorite;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.AcaraAdapter;
import com.dscunikom.android.sekolahqu.adapter.BeritaAdapter;
import com.dscunikom.android.sekolahqu.adapter.PrestasiAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.database.AcaraHelper;
import com.dscunikom.android.sekolahqu.database.BeritaHelper;
import com.dscunikom.android.sekolahqu.database.PrestasiHelper;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.prestasi.SpesifikSekolah;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends MvpFragment<FavoritePresenter> implements FavoriteView {

    Spinner spinner;
    RecyclerView rvFavBerita;
    RecyclerView rvFavAcara;
    RecyclerView rvFavPrestasi;
    ArrayList<BeritaModel> favoriteBerita = new ArrayList<>();
    ArrayList<AcaraModel> favoriteAcara = new ArrayList<>();
    ArrayList<SpesifikSekolah> favoritePrestasi = new ArrayList<>();
    BeritaHelper beritaHelper;
    AcaraHelper acaraHelper;
    PrestasiHelper prestasiHelper;
    SessionManager sessionManager;
    BeritaAdapter beritaAdapter;
    AcaraAdapter acaraAdapter;
    PrestasiAdapter prestasiAdapter;

    public FavoriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_favorite, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_favorite);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        presenter = createPresenter();

        rvFavBerita = rootView.findViewById(R.id.rv_favorite_berita);
        rvFavAcara = rootView.findViewById(R.id.rv_favorite_acara);
        rvFavPrestasi = rootView.findViewById(R.id.rv_favorite_prestasi);
        spinner = rootView.findViewById(R.id.spinner);

        rvFavBerita.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvFavBerita.setItemAnimator(new DefaultItemAnimator());
        rvFavBerita.addOnItemTouchListener(selectBeritaOnRecyclerView());

        rvFavAcara.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvFavAcara.setItemAnimator(new DefaultItemAnimator());
        rvFavAcara.addOnItemTouchListener(selectAcaraOnRecyclerView());

        rvFavPrestasi.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvFavPrestasi.setItemAnimator(new DefaultItemAnimator());
        rvFavPrestasi.addOnItemTouchListener(selectPrestasiOnRecyclerView());

        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.item_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              if (spinner.getSelectedItem().toString().equals("Berita")) {
                  beritaHelper = new BeritaHelper(getActivity().getApplicationContext());
                  beritaHelper.open();
                  favoriteBerita = beritaHelper.queryBerita(id_sekolah);
                  beritaHelper.close();
                  beritaAdapter = new BeritaAdapter(favoriteBerita, R.layout.item_content, getActivity());
                  rvFavBerita.setAdapter(beritaAdapter);
                  rvFavBerita.setVisibility(View.VISIBLE);
                  rvFavAcara.setVisibility(View.GONE);
                  rvFavPrestasi.setVisibility(View.GONE);
              } else if (spinner.getSelectedItem().toString().equals("Acara")) {
                  acaraHelper = new AcaraHelper(getActivity().getApplicationContext());
                  acaraHelper.open();
                  favoriteAcara = acaraHelper.queryAcara(id_sekolah);
                  acaraHelper.close();
                  acaraAdapter = new AcaraAdapter(favoriteAcara, R.layout.item_content, getActivity());
                  rvFavAcara.setAdapter(acaraAdapter);
                  rvFavBerita.setVisibility(View.GONE);
                  rvFavPrestasi.setVisibility(View.GONE);
                  rvFavAcara.setVisibility(View.VISIBLE);
              } else if (spinner.getSelectedItem().toString().equals("Prestasi")) {
                  prestasiHelper = new PrestasiHelper(getActivity().getApplicationContext());
                  prestasiHelper.open();
                  favoritePrestasi = prestasiHelper.queryPrestasi(id_sekolah);
                  prestasiHelper.close();
                  prestasiAdapter = new PrestasiAdapter(favoritePrestasi, R.layout.item_content, getActivity());
                  rvFavPrestasi.setAdapter(prestasiAdapter);
                  rvFavPrestasi.setVisibility(View.VISIBLE);
                  rvFavAcara.setVisibility(View.GONE);
                  rvFavBerita.setVisibility(View.GONE);
              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
    }

    private RecyclerItemClickListener selectBeritaOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvFavBerita, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdBerita(favoriteBerita.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdBerita(favoriteBerita.get(position), activity);
            }
        });
    }

    private RecyclerItemClickListener selectAcaraOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvFavAcara, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdAcara(favoriteAcara.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdAcara(favoriteAcara.get(position), activity);
            }
        });
    }

    private RecyclerItemClickListener selectPrestasiOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvFavPrestasi, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToPrestasi(favoritePrestasi.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                presenter.getIdToPrestasi(favoritePrestasi.get(position), activity);
            }
        });
    }

    @Override
    protected FavoritePresenter createPresenter() {
        return new FavoritePresenter(this);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
