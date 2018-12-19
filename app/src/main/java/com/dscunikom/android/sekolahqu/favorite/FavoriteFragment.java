package com.dscunikom.android.sekolahqu.favorite;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dscunikom.android.sekolahqu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_favorite, container, false);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar_favorite);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        return rootView;
    }

}
