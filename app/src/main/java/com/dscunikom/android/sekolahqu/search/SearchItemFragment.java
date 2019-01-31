package com.dscunikom.android.sekolahqu.search;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.adapter.AcaraAdapter;
import com.dscunikom.android.sekolahqu.adapter.BeritaAdapter;
import com.dscunikom.android.sekolahqu.adapter.PrestasiAdapter;
import com.dscunikom.android.sekolahqu.base.mvp.MvpFragment;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchItemFragment extends MvpFragment<SearchItemPresenter> implements SearchItemView {

    android.widget.SearchView searchView;
    Button btnBerita, btnAcara , btnPrestasi;
    RecyclerView rvBerita, rvAcara, rvPrestasi;
    SessionManager sessionManager;

    private List<BeritaModel> listBerita,  listBeritaFilter;
    private List<AcaraModel> listAcara , listAcaraFilter;
    private List<Prestasi> listPrestasi, listPrestasiFilter;

    private boolean clickedBerita = false;
    private boolean clickedAcara = false;
    private boolean clickedPrestasi = false;

    TextView tvDataKosong, tvPilihKategori;

    public SearchItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_search, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        presenter = createPresenter();

        btnBerita = view.findViewById(R.id.btn_berita);
        btnAcara = view.findViewById(R.id.btn_acara);
        btnPrestasi = view.findViewById(R.id.btn_prestasi);
        rvBerita = view.findViewById(R.id.rv_search_berita);
        rvAcara = view.findViewById(R.id.rv_search_acara);
        rvPrestasi = view.findViewById(R.id.rv_search_prestasi);
        tvDataKosong = view.findViewById(R.id.tv_kosong_search);
        tvPilihKategori = view.findViewById(R.id.tv_pilih_kategori);
        tvDataKosong.setVisibility(View.GONE);
        tvPilihKategori.setVisibility(View.VISIBLE);

        rvBerita.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvBerita.addOnItemTouchListener(selectItemBeritaOnRecyclerView());
        rvBerita.setItemAnimator(new DefaultItemAnimator());

        rvAcara.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvAcara.addOnItemTouchListener(selectItemAcaraOnRecyclerView());
        rvAcara.setItemAnimator(new DefaultItemAnimator());

        rvPrestasi.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvPrestasi.addOnItemTouchListener(selectItemPrestasiOnRecyclerView());
        rvPrestasi.setItemAnimator(new DefaultItemAnimator());

        sessionManager = new SessionManager(this.getActivity());
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);


        btnBerita.setOnClickListener(view1 -> {
            clickedBerita = true;
            clickedPrestasi = false;
            clickedAcara = false;
            rvAcara.setVisibility(View.GONE);
            rvPrestasi.setVisibility(View.GONE);
            rvBerita.setVisibility(View.VISIBLE);
            presenter.getDataBerita(id_sekolah);
            searchView.setVisibility(View.VISIBLE);
        });

        btnAcara.setOnClickListener(view12 -> {
            clickedAcara = true;
            clickedPrestasi = false;
            clickedBerita = false;
            rvBerita.setVisibility(View.GONE);
            rvPrestasi.setVisibility(View.GONE);
            rvAcara.setVisibility(View.VISIBLE);
            presenter.getDataAcara(id_sekolah);
            searchView.setVisibility(View.VISIBLE);
        });

        btnPrestasi.setOnClickListener(view13 -> {
            clickedPrestasi = true;
            clickedAcara = false;
            clickedBerita = false;
            rvAcara.setVisibility(View.GONE);
            rvBerita.setVisibility(View.GONE);
            rvPrestasi.setVisibility(View.VISIBLE);
            presenter.getDataPrestasi(id_sekolah);
            searchView.setVisibility(View.VISIBLE);
        });

        return view;
    }

    private RecyclerItemClickListener selectItemBeritaOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvBerita, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
              presenter.getIdBerita(listBerita.get(position), activity);
          }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    private RecyclerItemClickListener selectItemAcaraOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvAcara, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdAcara(listAcara.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    private RecyclerItemClickListener selectItemPrestasiOnRecyclerView() {
        return new RecyclerItemClickListener(getActivity(), rvPrestasi, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToPrestasi(listPrestasi.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view_menu, menu);
        searchView = (android.widget.SearchView) menu.findItem(R.id.mnSearchView).getActionView();
        searchView.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchItem(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchItem(s);
                return true;
            }
        });


    }

    private void searchItem(String query) {
        if (clickedBerita) {
            searchBerita(query);
//            if (listBeritaFilter.size() == 0) {
//                rvBerita.setVisibility(View.GONE);
//                tvDataKosong.setVisibility(View.VISIBLE);
//            } else {
//                rvBerita.setVisibility(View.VISIBLE);
//                tvDataKosong.setVisibility(View.GONE);
//            }
        } else if (clickedAcara) {
            searchAcara(query);
//            if (listAcaraFilter.size() == 0) {
//                rvAcara.setVisibility(View.GONE);
//                tvDataKosong.setVisibility(View.VISIBLE);
//            } else {
//                rvAcara.setVisibility(View.VISIBLE);
//                tvDataKosong.setVisibility(View.GONE);
//            }
        } else if (clickedPrestasi) {
            searchPrestasi(query);
//            if (listPrestasiFilter.size() == 0) {
//                rvPrestasi.setVisibility(View.GONE);
//                tvDataKosong.setVisibility(View.VISIBLE);
//            } else {
//                rvPrestasi.setVisibility(View.VISIBLE);
//                tvDataKosong.setVisibility(View.GONE);
//            }
        }
    }

    @Override
    protected SearchItemPresenter createPresenter() {
        return new SearchItemPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void searchBerita(String keyword) {
        final List<BeritaModel> filteredList = new ArrayList<>();

        for (BeritaModel s : listBeritaFilter) {
            if (s.getNamaBerita().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(s);
            }
        }
        if (filteredList.size() == 0) {
            rvBerita.setVisibility(View.GONE);
            tvDataKosong.setVisibility(View.VISIBLE);
        } else {
            rvBerita.setVisibility(View.VISIBLE);
            tvDataKosong.setVisibility(View.GONE);
            RecyclerView.Adapter fAdapter = new BeritaAdapter(filteredList, R.layout.item_content, this.getActivity());
            reloadViewBerita(fAdapter, filteredList);
        }

    }

    private void searchAcara(String keyword) {
        final List<AcaraModel> filteredList = new ArrayList<>();
        for (AcaraModel s : listAcaraFilter) {
            if (s.getNamaAcara().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(s);
            }
        }
        if (filteredList.size() == 0) {
            rvAcara.setVisibility(View.GONE);
            tvDataKosong.setVisibility(View.VISIBLE);
        } else {
            rvAcara.setVisibility(View.VISIBLE);
            tvDataKosong.setVisibility(View.GONE);
            RecyclerView.Adapter fAdapter = new AcaraAdapter(filteredList, R.layout.item_content, this.getActivity());
            reloadViewAcara(fAdapter, filteredList);
        }

    }

    private void searchPrestasi(String keyword) {
        final List<Prestasi> filteredList = new ArrayList<>();
        for (Prestasi s : listPrestasiFilter) {
            if (s.getNamaPrestasi().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(s);
            }
        }
        if (filteredList.size() == 0) {
            rvPrestasi.setVisibility(View.GONE);
            tvDataKosong.setVisibility(View.VISIBLE);
        } else {
            rvPrestasi.setVisibility(View.VISIBLE);
            tvDataKosong.setVisibility(View.GONE);
            RecyclerView.Adapter fAdapter = new PrestasiAdapter(filteredList, R.layout.item_content, this.getActivity());
            reloadViewPrestasi(fAdapter, filteredList);
        }

    }

    private void reloadViewPrestasi(RecyclerView.Adapter adapter, List<Prestasi> list) {
        rvPrestasi.setAdapter(adapter);
        new RecyclerItemClickListener(getActivity(), rvPrestasi, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdToPrestasi(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    private void reloadViewAcara(RecyclerView.Adapter adapter, List<AcaraModel> list) {
        rvAcara.setAdapter(adapter);
        new RecyclerItemClickListener(getActivity(), rvAcara, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdAcara(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    private void reloadViewBerita(RecyclerView.Adapter adapter, List<BeritaModel> list) {
        rvBerita.setAdapter(adapter);
        new RecyclerItemClickListener(getActivity(), rvBerita, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getIdBerita(list.get(position), activity);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void showListBerita(BeritaResponse model) {
        this.listBerita = model.getSpesifikSekolah();
        this.listBeritaFilter = model.getSpesifikSekolah();
        if (listBerita.size() == 0) {
            tvDataKosong.setVisibility(View.VISIBLE);
            rvBerita.setVisibility(View.GONE);
            tvPilihKategori.setVisibility(View.GONE);
        } else {
            rvBerita.setAdapter(new BeritaAdapter(listBerita, R.layout.item_content, this.getActivity()));
            tvDataKosong.setVisibility(View.GONE);
            tvPilihKategori.setVisibility(View.GONE);
            rvBerita.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showListBeritaFailed(String message) {
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        tvDataKosong.setVisibility(View.VISIBLE);
        rvBerita.setVisibility(View.GONE);
        tvPilihKategori.setVisibility(View.GONE);
    }

    @Override
    public void showListAcara(AcaraResponse model) {
        this.listAcara = model.getSpesifikSekolah();
        this.listAcaraFilter = model.getSpesifikSekolah();
        if (listAcara.size() == 0) {
            tvDataKosong.setVisibility(View.VISIBLE);
            rvAcara.setVisibility(View.GONE);
            tvPilihKategori.setVisibility(View.GONE);
        } else {
            rvAcara.setAdapter(new AcaraAdapter(listAcara, R.layout.item_content, this.getActivity()));
            tvDataKosong.setVisibility(View.GONE);
            rvAcara.setVisibility(View.VISIBLE);
            tvPilihKategori.setVisibility(View.GONE);
        }

    }

    @Override
    public void showListAcaraFailed(String message) {
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        tvDataKosong.setVisibility(View.VISIBLE);
        rvAcara.setVisibility(View.GONE);
        tvPilihKategori.setVisibility(View.GONE);
    }

    @Override
    public void showListPrestasi(PrestasiResponse model) {
        this.listPrestasi = model.getSpesifikSekolah();
        this.listPrestasiFilter = model.getSpesifikSekolah();
        if (listPrestasi.size() == 0) {
            tvDataKosong.setVisibility(View.VISIBLE);
            rvPrestasi.setVisibility(View.GONE);
            tvPilihKategori.setVisibility(View.GONE);
        } else {
            rvPrestasi.setAdapter(new PrestasiAdapter(listPrestasi, R.layout.item_content, this.getActivity()));
            tvDataKosong.setVisibility(View.GONE);
            rvPrestasi.setVisibility(View.VISIBLE);
            tvPilihKategori.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListPrestasiFailed(String message) {
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        tvDataKosong.setVisibility(View.VISIBLE);
        rvPrestasi.setVisibility(View.GONE);
        tvPilihKategori.setVisibility(View.GONE);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }

}
