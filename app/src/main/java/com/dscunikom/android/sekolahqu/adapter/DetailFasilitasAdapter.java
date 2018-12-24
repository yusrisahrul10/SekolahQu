package com.dscunikom.android.sekolahqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas.Gambar;

import java.util.List;

public class DetailFasilitasAdapter extends RecyclerView.Adapter<DetailFasilitasAdapter.DetailFasilitasViewHolder> {
    private List<Gambar> mListGambar;
    private int rowLayout;
    Context context;

    public DetailFasilitasAdapter(List<Gambar> mListGambar, int rowLayout, Context context) {
        this.mListGambar = mListGambar;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailFasilitasAdapter.DetailFasilitasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new DetailFasilitasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailFasilitasAdapter.DetailFasilitasViewHolder detailFasilitasViewHolder, int i) {
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/fasilitas/"+mListGambar.get(i).getImage())
                .into(detailFasilitasViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mListGambar.size();
    }

    public class DetailFasilitasViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public DetailFasilitasViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_detail_fasilitas);
        }
    }
}
