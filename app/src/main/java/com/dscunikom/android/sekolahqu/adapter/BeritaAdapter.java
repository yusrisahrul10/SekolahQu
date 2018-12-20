package com.dscunikom.android.sekolahqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.berita.SpesifikSekolah;

import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
    private List<SpesifikSekolah> mListBerita;
    private int rowLayout;
    Context context;

    public BeritaAdapter(List<SpesifikSekolah> mListBerita, int rowLayout, Context context) {
        this.mListBerita = mListBerita;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder beritaViewHolder, int i) {
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/berita/"+mListBerita.get(i).getImage())
                .into(beritaViewHolder.imageView);
        beritaViewHolder.tvJudul.setText(mListBerita.get(i).getNamaBerita());
        beritaViewHolder.tvDeskripsi.setText(mListBerita.get(i).getDeskripsi());
        beritaViewHolder.tvTanggal.setText(mListBerita.get(i).getTanggalBerita());
    }

    @Override
    public int getItemCount() {
        return mListBerita.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvJudul , tvTanggal ,tvDeskripsi;
        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_content);
            tvTanggal = itemView.findViewById(R.id.tv_date_content);
            tvJudul = itemView.findViewById(R.id.tv_title_content);
            tvDeskripsi =  itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
