package com.dscunikom.android.sekolahqu.preload;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.preload.sekolahlist.SekolahListActivity;

public class PreloadActivity extends AppCompatActivity {

    @BindView(R.id.iv_first)
    ImageView image;
    private boolean mStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        ButterKnife.bind(this);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), SekolahListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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
}
