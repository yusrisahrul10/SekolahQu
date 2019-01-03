package com.dscunikom.android.sekolahqu.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager;

public class SwitchActivity extends AppCompatActivity {

    Switch swNotif;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        swNotif = findViewById(R.id.sw_notif);
        sessionManager = new SessionManager(this);
        boolean isNotif = sessionManager.getNotif();
        setNotif();
        if (isNotif) swNotif.setChecked(true);
    }

    public void setNotif() {
        swNotif.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                sessionManager.creatSession();
            } else {
                sessionManager.dropNotif();
            }
        });
    }
}
