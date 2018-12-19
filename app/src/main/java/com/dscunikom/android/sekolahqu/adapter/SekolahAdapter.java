package com.dscunikom.android.sekolahqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.Sekolah;

import java.util.List;

public class SekolahAdapter extends RecyclerView.Adapter<SekolahAdapter.SekolahViewHolder> {

    private List<Sekolah> sekolah;
    private int rowLayout;
    private Context context;

    static class SekolahViewHolder extends RecyclerView.ViewHolder{

        TextView tvNamaSekolah;
        ImageView ivLogoSekolah;

        public SekolahViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaSekolah = itemView.findViewById(R.id.tv_nama_sekolah);
            ivLogoSekolah = itemView.findViewById(R.id.iv_logo_sekolah);
        }
    }

    public SekolahAdapter(List<Sekolah> sekolah, int rowLayout, Context context) {
        this.sekolah = sekolah;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public SekolahAdapter.SekolahViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new SekolahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SekolahAdapter.SekolahViewHolder holder, int position) {
        holder.tvNamaSekolah.setText(sekolah.get(position).getNamaSekolah());
//        Glide.with(context).load(sekolah.get(position).getLogoSekolah()).into(holder.ivLogoSekolah);
        Log.e("Size:", "Size: "+sekolah.size());
    }

    @Override
    public int getItemCount() {
        return sekolah.size();

    }
}
