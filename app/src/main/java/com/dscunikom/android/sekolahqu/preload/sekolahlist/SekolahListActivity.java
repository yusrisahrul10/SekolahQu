package com.dscunikom.android.sekolahqu.preload.sekolahlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.dscunikom.android.sekolahqu.utils.RecyclerItemClickListener;
import com.dscunikom.android.sekolahqu.adapter.SekolahAdapter;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.model.sekolah.SekolahResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SekolahListActivity extends MvpActivity<SekolahListPresenter> implements SekolahListView {

    private List<Sekolah> list, listSekolahFilter;
    @BindView(R.id.rv_list_sekolah)
    RecyclerView recyclerView;
    @BindView(R.id.progress_list_sekolah)
    ProgressBar progressBar;
    @BindView(R.id.tv_kosong_list_sekolah)
    TextView tvDataKosong;
    @BindView(R.id.swipe_list_sekolah)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.search_list_sekolah)
    SearchView searchView;
    SessionManager sessionManager;

    @Override
    protected SekolahListPresenter createPresenter() {
        return new SekolahListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekolah_list);
        sessionManager = new SessionManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(selectItemOnRecyclerView());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter.loadData();
        swipeRefresh.setOnRefreshListener(() -> presenter.loadData());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSekolah(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchSekolah(newText);
                return true;
            }
        });
    }

    private RecyclerItemClickListener selectItemOnRecyclerView() {
        return new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String , String> sekolah = sessionManager.getSekolahPref();
                String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH_NOTIF);
                if(sessionManager.getNotif() == false || id_sekolah == null ){
                    presenter.getSekolahToNotif(list.get(position), activity);

                }else{
                    presenter.getSekolahToHome(list.get(position),activity);
                }
                sessionManager.createIdSekolah(list.get(position).getIdSekolah());

                Log.e("Message", "Message: "+ list.get(position).getNamaSekolah());
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        });
    }

    private void searchSekolah(String keyword) {
        final List<Sekolah> filteredList = new ArrayList<>();
        for (Sekolah s : listSekolahFilter) {
            if (s.getNamaSekolah().toLowerCase().contains(keyword)) {
                filteredList.add(s);
            }
        }
        RecyclerView.Adapter fAdapter = new SekolahAdapter(filteredList, R.layout.item_sekolah, getApplicationContext());
        reloadViewSekolah(fAdapter, filteredList);
    }

    private void reloadViewSekolah(RecyclerView.Adapter adapter, List<Sekolah> list){
        recyclerView.setAdapter(adapter);
        new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HashMap<String , String> sekolah = sessionManager.getSekolahPref();
                String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH_NOTIF);
                if(sessionManager.getNotif() == false || id_sekolah == null ){
                    presenter.getSekolahToNotif(list.get(position), activity);

                }else{
                    presenter.getSekolahToHome(list.get(position),activity);
                }
                sessionManager.createIdSekolah(list.get(position).getIdSekolah());

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tvDataKosong.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSekolahListSuccess(SekolahResponse model) {
        swipeRefresh.setRefreshing(false);
        this.list = model.getResult();
        this.listSekolahFilter = model.getResult();
        recyclerView.setAdapter(new SekolahAdapter(list, R.layout.item_sekolah, getApplicationContext()));
        Log.e("Model", "Model: "+ model.getResult());
    }

    @Override
    public void showSekolahListFailed(String message) {
        swipeRefresh.setRefreshing(false);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        tvDataKosong.setVisibility(View.VISIBLE);
    }

    @Override
    public void moveToActivity(Intent intent) {
        startActivity(intent);
    }
}
