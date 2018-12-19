package com.dscunikom.android.sekolahqu.home.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dscunikom.android.sekolahqu.detail.DetailBeritaActivity;
import com.dscunikom.android.sekolahqu.R;

public class NewsFragment extends Fragment {

    CardView cvNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_news, container, false);


        cvNews = rootView.findViewById(R.id.cv_news);
        cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailBeritaActivity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }


}
