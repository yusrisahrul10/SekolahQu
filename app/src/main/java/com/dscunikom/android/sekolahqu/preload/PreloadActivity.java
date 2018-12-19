package com.dscunikom.android.sekolahqu.preload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dscunikom.android.sekolahqu.R;

public class PreloadActivity extends AppCompatActivity {

    @BindView(R.id.iv_first)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        ButterKnife.bind(this);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreloadActivity.this, SekolahListActivity.class);
                startActivity(intent);
            }
        });
    }
}
