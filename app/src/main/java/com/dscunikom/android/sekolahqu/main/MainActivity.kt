package com.dscunikom.android.sekolahqu.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dscunikom.android.sekolahqu.favorite.FavoriteFragment
import com.dscunikom.android.sekolahqu.home.HomeFragment
import com.dscunikom.android.sekolahqu.R
import com.dscunikom.android.sekolahqu.preload.SekolahListActivity
import com.dscunikom.android.sekolahqu.search.SearchFragment
import com.dscunikom.android.sekolahqu.sharedpref.SessionManager
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sessionManager = SessionManager(this)
        val sekolah = sessionManager.getSekolahPref()
        val id_sekolah = sekolah.get(SessionManager.ID_SEKOLAH)
        val setIDNotif = sekolah.get(SessionManager.ID_SEKOLAH_NOTIF)
        val test = sessionManager.notif

        if(setIDNotif != null){
          FirebaseMessaging.getInstance().subscribeToTopic(setIDNotif)
            Log.e("Berhasil ","Notification Testing HELSAN")
        }
//        Log.e("Helsan","Boolean : "+test+"ID NOTIF : "+setIDNotif)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnv_home -> {
                    loadHomeFragment(savedInstanceState)
                }
                R.id.bnv_favorite -> {
                    loadFavoriteFragment(savedInstanceState)
                }
                R.id.bnv_search -> {
                    loadSearchFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.bnv_home
    }

    private fun loadHomeFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_layout,
                    HomeFragment(), HomeFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_layout,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadSearchFragment(savedInstanceState: Bundle?) {
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_layout,
                    SearchFragment(), SearchFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val goToMainActivity = Intent(applicationContext, SekolahListActivity::class.java)
        goToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Will clear out your activity history stack till now
        startActivity(goToMainActivity)

    }
}
