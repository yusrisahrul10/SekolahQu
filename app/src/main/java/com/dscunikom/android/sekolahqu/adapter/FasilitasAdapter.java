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
import com.dscunikom.android.sekolahqu.model.fasilitas.Fasilitas;

import java.util.List;

public class FasilitasAdapter extends RecyclerView.Adapter<FasilitasAdapter.ViewHolder> {

    private List<Fasilitas> list;
    private int rowLayout;
    private Context context;

    public FasilitasAdapter(List<Fasilitas> list, int rowLayout, Context context) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFasilitas;
        TextView tvFasilitas;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFasilitas = itemView.findViewById(R.id.image_ekskul_fasilitas);
            tvFasilitas = itemView.findViewById(R.id.text_ekskul_fasilitas);
        }
    }

    @NonNull
    @Override
    public FasilitasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FasilitasAdapter.ViewHolder viewHolder, int position) {
        viewHolder.tvFasilitas.setText(list.get(position).getNamaFasilitas());
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/fasilitas/"+list.get(position).getImage())
                .into(viewHolder.ivFasilitas);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
