package com.dscunikom.android.sekolahqu.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
        Context context;
    int mode = 0;

    private static final String PREF_NAME = "SessionPref";
    private static final String IS_NOTIF = "is_notif";
    public static final String ID_SEKOLAH = "id_sekolah";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,mode);
        editor = pref.edit();
    }
    public void creatSession(){
        editor.putBoolean(IS_NOTIF,true);
        editor.commit();
    }
    public void createIdSekolah(String id_sekolah){
        editor.putString(ID_SEKOLAH,id_sekolah);
        editor.commit();
    }
    public boolean is_notif(){
        return pref.getBoolean(IS_NOTIF,false);
    }

    public HashMap<String, String> getSekolahPref(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(ID_SEKOLAH,pref.getString(ID_SEKOLAH,null));
        return user;

    }
}
