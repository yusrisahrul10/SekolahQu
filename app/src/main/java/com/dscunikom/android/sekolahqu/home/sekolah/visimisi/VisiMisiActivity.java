package com.dscunikom.android.sekolahqu.home.sekolah.visimisi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.base.mvp.MvpActivity;
import com.dscunikom.android.sekolahqu.model.Sekolah;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

import java.util.HashMap;

public class VisiMisiActivity extends MvpActivity<VisiMisiPresenter> implements VisiMisiView {
    @BindView(R.id.imageDetail)
    ImageView imgDetail;
    @BindView(R.id.txtIsiBerita)
    TextView tvVisiMisi;
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
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showDetailSekolah(Sekolah model) {
                Glide.with(getApplicationContext())
                        .load("http://sekolahqu.dscunikom.com/uploads/profile_sekolah/"+model.getLogoSekolah())
                        .into(imgDetail);
                tvVisiMisi.setText(model.getVisiMisi());

    }

    @Override
    public void showSekolahListFailed(String message) {

    }
}
