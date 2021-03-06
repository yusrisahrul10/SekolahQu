package com.dscunikom.android.sekolahqu.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dscunikom.android.sekolahqu.R
import com.dscunikom.android.sekolahqu.adapter.ViewPagerAdapter
import com.dscunikom.android.sekolahqu.home.prestasi.PrestasiFragment
import com.dscunikom.android.sekolahqu.home.acara.AcaraFragment
import com.dscunikom.android.sekolahqu.home.berita.BeritaFragment
import com.dscunikom.android.sekolahqu.home.sekolah.sekolah.SekolahFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as AppCompatActivity) {
            setSupportActionBar(toolbar_home)

            view_pager.adapter = ViewPagerAdapter(
                supportFragmentManager,
                mapOf(
                    getString(R.string.sekolah) to SekolahFragment(),
                    getString(R.string.berita) to BeritaFragment(),
                    getString(R.string.acara) to AcaraFragment(),
                    getString(R.string.prestasi) to PrestasiFragment()
                )
            )
            tab_layout.setupWithViewPager(view_pager)
        }
    }
}