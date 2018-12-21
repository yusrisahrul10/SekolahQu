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
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;

import java.util.List;

public class AcaraAdapter extends RecyclerView.Adapter<AcaraAdapter.AcaraViewHolder> {
    private List<AcaraModel> mListAcara;
    private int rowLayout;
    Context context;

    public AcaraAdapter(List<AcaraModel> mListAcara, int rowLayout, Context context) {
        this.mListAcara = mListAcara;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public AcaraAdapter.AcaraViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new AcaraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcaraAdapter.AcaraViewHolder acaraViewHolder, int i) {
        Glide.with(context)
                .load("http://sekolahqu.dscunikom.com/uploads/acara/"+mListAcara.get(i).getImage())
                .into(acaraViewHolder.imageView);
        acaraViewHolder.tvJudul.setText(mListAcara.get(i).getNamaAcara());
        acaraViewHolder.tvDeskripsi.setText(mListAcara.get(i).getDeskripsi());
        acaraViewHolder.tvTanggal.setText(mListAcara.get(i).getTanggalAcara());
    }

    @Override
    public int getItemCount() {
        return mListAcara.size();
    }

    public class AcaraViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvJudul , tvTanggal ,tvDeskripsi;
        public AcaraViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_content);
            tvTanggal = itemView.findViewById(R.id.tv_date_content);
            tvJudul = itemView.findViewById(R.id.tv_title_content);
            tvDeskripsi =  itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
