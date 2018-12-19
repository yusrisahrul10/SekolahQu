package com.dscunikom.android.sekolahqu.preload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dscunikom.android.sekolahqu.main.MainActivity;
import com.dscunikom.android.sekolahqu.R;

public class NotifActivity extends AppCompatActivity {

    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        ButterKnife.bind(this);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
