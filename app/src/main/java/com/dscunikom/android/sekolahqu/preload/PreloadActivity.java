package com.dscunikom.android.sekolahqu.preload;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.trafic.AuthResponse;
import com.dscunikom.android.sekolahqu.network.NetworkClient;
import com.dscunikom.android.sekolahqu.network.NetworkStores;
import com.dscunikom.android.sekolahqu.preload.sekolahlist.SekolahListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreloadActivity extends AppCompatActivity {

    @BindView(R.id.iv_first)
    ImageView image;
    private boolean mStarted = false;
    private String ANDROID_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        ButterKnife.bind(this);
        ANDROID_ID = Settings.Secure.getString(this.getApplication().getContentResolver(),Settings.Secure.ANDROID_ID);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), SekolahListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            postID();
            finish();
        }, 2000);
    }

    @Override
    protected void onStart() {
        mStarted = true;
        super.onStart();
    }

    @Override
    protected void onStop() {
        mStarted = false;
        super.onStop();
    }

    private void postID(){
        NetworkStores networkStores = NetworkClient.getRetrofit().create(NetworkStores.class);
        Call<AuthResponse> authResponseCall = networkStores.postRespone(ANDROID_ID);
        authResponseCall.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.body().getResponse().equals("success")){
                    Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG);
                }else{
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {

            }
        });
    }
}
