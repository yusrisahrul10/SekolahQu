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
import com.dscunikom.android.sekolahqu.model.ekskul.Ekskul;

import java.util.List;

public class EkskulAdapter extends RecyclerView.Adapter<EkskulAdapter.ViewHolder> {

    private List<Ekskul> list;
    private int rowLayout;
    private Context context;

    public EkskulAdapter(List<Ekskul> list, int rowLayout, Context context) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEkskul;
        TextView tvEkskul;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEkskul = itemView.findViewById(R.id.image_ekskul_fasilitas);
            tvEkskul = itemView.findViewById(R.id.text_ekskul_fasilitas);
        }
    }

    @NonNull
    @Override
    public EkskulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EkskulAdapter.ViewHolder viewHolder, int position) {
        viewHolder.tvEkskul.setText(list.get(position).getNamaEkskul());
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/ekskul/" + list.get(position).getImage())
                .into(viewHolder.ivEkskul);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
