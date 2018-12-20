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
import com.dscunikom.android.sekolahqu.model.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.SpesifikSekolah;

import java.util.List;

public class PrestasiAdapter extends  RecyclerView.Adapter<PrestasiAdapter.PrestasiViewHolder> {
    private List<SpesifikSekolah> list;
    private int rowLayout;
    private Context context;

    public PrestasiAdapter(List<SpesifikSekolah> list, int rowLayout, Context context) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public PrestasiAdapter.PrestasiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new PrestasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestasiAdapter.PrestasiViewHolder prestasiViewHolder, int i) {
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/prestasi/"+list.get(i).getImage())
                .into(prestasiViewHolder.imageView);
        prestasiViewHolder.tvJudul.setText(list.get(i).getNamaPrestasi());
        prestasiViewHolder.tvDeskripsi.setText(list.get(i).getDeskripsi());
        prestasiViewHolder.tvTanggal.setText(list.get(i).getTanggalDidapat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PrestasiViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvJudul , tvTanggal ,tvDeskripsi;
        public PrestasiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_content);
            tvTanggal = itemView.findViewById(R.id.tv_date_content);
            tvJudul = itemView.findViewById(R.id.tv_title_content);
            tvDeskripsi =  itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
