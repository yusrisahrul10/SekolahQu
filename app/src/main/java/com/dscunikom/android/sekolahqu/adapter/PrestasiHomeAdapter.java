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
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;

import java.util.List;

public class PrestasiHomeAdapter extends RecyclerView.Adapter<PrestasiHomeAdapter.PrestasiHomeViewHolder> {

    private List<Prestasi> list;
    private int rowLayout;
    private Context context;

    public PrestasiHomeAdapter(List<Prestasi> list, int rowLayout, Context context) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    class PrestasiHomeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPrestasi;
        TextView tvPrestasi;

        public PrestasiHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPrestasi = itemView.findViewById(R.id.image_prestasi);
            tvPrestasi = itemView.findViewById(R.id.txtJudulPrestasi);
        }
    }

    @NonNull
    @Override
    public PrestasiHomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new PrestasiHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestasiHomeViewHolder holder, int i) {
        holder.tvPrestasi.setText(list.get(i).getNamaPrestasi().substring(0,12)+"...");
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/prestasi/"+list.get(i).getImage())
                .into(holder.ivPrestasi);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
