package com.dscunikom.android.sekolahqu.home.sekolah.visimisi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

import java.util.HashMap;

public class VisiMisiActivity extends MvpActivity<VisiMisiPresenter> implements VisiMisiView {
    @BindView(R.id.imageDetail)
    ImageView imgDetail;
    @BindView(R.id.txtIsiBerita)
    TextView tvVisiMisi;
    @BindView(R.id.txtJudul)
    TextView tvJudul;
    @BindView(R.id.swipe_visi_misi)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.progress_bar_visi_misi)
    ProgressBar progressBar;

    SessionManager sessionManager;
    @Override
    protected VisiMisiPresenter createPresenter() {
        return new VisiMisiPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visi_misi);
        sessionManager = new SessionManager(this);
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
        presenter.getData(id_sekolah);
        swipeRefresh.setOnRefreshListener(() -> presenter.getData(id_sekolah));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        imgDetail.setVisibility(View.INVISIBLE);
        tvVisiMisi.setVisibility(View.GONE);
        tvJudul.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        imgDetail.setVisibility(View.VISIBLE);
        tvVisiMisi.setVisibility(View.VISIBLE);
        tvJudul.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailSekolah(Sekolah model) {
        swipeRefresh.setRefreshing(false);
                Glide.with(getApplicationContext())
                        .load("http://sekolahqu.dscunikom.com/uploads/profile_sekolah/"+model.getLogoSekolah())
                        .into(imgDetail);
                tvVisiMisi.setText(model.getVisiMisi());
        tvJudul.setText("VISI MISI");
    }

    @Override
    public void showSekolahListFailed(String message) {
        swipeRefresh.setRefreshing(false);
        message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        imgDetail.setVisibility(View.VISIBLE);
        tvVisiMisi.setVisibility(View.GONE);
        tvJudul.setVisibility(View.VISIBLE);

        Glide.with(getApplicationContext())
                .load(R.drawable.empty)
                .into(imgDetail);
        tvJudul.setText("Tidak Ada Data");
    }
}
