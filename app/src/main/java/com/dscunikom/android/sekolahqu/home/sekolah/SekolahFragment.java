package com.dscunikom.android.sekolahqu.home.sekolah;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dscunikom.android.sekolahqu.*;
import com.dscunikom.android.sekolahqu.home.sekolah.ekskul.EkskulActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.fasilitas.FasilitasActivity;
import com.dscunikom.android.sekolahqu.home.sekolah.visimisi.VisiMisiActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SekolahFragment extends Fragment {


    public SekolahFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_sekolah, container, false);

        CardView btnVisiMisi = rootView.findViewById(R.id.btn_visi_misi);
        CardView btnFasilitas = rootView.findViewById(R.id.btn_fasilitas);
        CardView btnEkskul = rootView.findViewById(R.id.btn_ekskul);
        CardView btnKalender = rootView.findViewById(R.id.btn_kalender);

        btnVisiMisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VisiMisiActivity.class);
                startActivity(intent);
            }
        });

        btnFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FasilitasActivity.class);
                startActivity(intent);
            }
        });

        btnEkskul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EkskulActivity.class);
                startActivity(intent);
            }
        });

        btnKalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KalenderActivity.class);
                startActivity(intent);
            }
        });
//        ButterKnife.bind(rootView);
        return rootView;
    }

}
