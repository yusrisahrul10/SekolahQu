package com.dscunikom.android.sekolahqu.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import com.dscunikom.android.sekolahqu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    android.widget.SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view_menu, menu);
        searchView = (android.widget.SearchView) menu.findItem(R.id.mnSearchView).getActionView();


        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });

//        super.onCreateOptionsMenu(menu, inflater);

    }
}
