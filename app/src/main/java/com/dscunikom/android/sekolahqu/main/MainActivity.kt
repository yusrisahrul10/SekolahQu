package com.dscunikom.android.sekolahqu.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dscunikom.android.sekolahqu.favorite.FavoriteFragment
import com.dscunikom.android.sekolahqu.home.HomeFragment
import com.dscunikom.android.sekolahqu.R
import com.dscunikom.android.sekolahqu.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

}
