package com.dscunikom.android.sekolahqu.preload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dscunikom.android.sekolahqu.main.MainActivity;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class NotifActivity extends AppCompatActivity {

    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    SessionManager sessionManager;
    String id;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sessionManager = new SessionManager(this);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id_sekolah");
        sessionManager = new SessionManager(this);
        HashMap<String , String> sekolah = sessionManager.getSekolahPref();
        String id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH);
//        FirebaseMessaging.getInstance().subscribeToTopic(id_sekolah);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifActivity.this, MainActivity.class);
                sessionManager.creatSession();
                sessionManager.setIdSekolah(id);
                startActivity(intent);
                String name = "btnYesNotif";
                Bundle params = new Bundle();
                params.putString("btnYesNotif", name);
                mFirebaseAnalytics.logEvent("Button_YES_NOTIF",params);
                Log.e("Firebase Analytics ","Error : ");

//                finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifActivity.this, MainActivity.class);
                sessionManager.dropNotif();
                startActivity(intent);
                String name = "btnNONotif";
                Bundle params = new Bundle();
                params.putString("btnNONotif", name);
                mFirebaseAnalytics.logEvent("Button_NO_NOTIF",params);
                Log.e("Firebase Analytics ","Error : ");
            }
        });
    }
}
