package com.dscunikom.android.sekolahqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dscunikom.android.sekolahqu.R;
import com.dscunikom.android.sekolahqu.model.kalender.Kalender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterKalender extends RecyclerView.Adapter<AdapterKalender.KalenderViewHolder> {
    List<Kalender> mListKalender;
    private int rowLayout;
    Context context;

    public AdapterKalender(List<Kalender> mListKalender, int rowLayout, Context context) {
        this.mListKalender = mListKalender;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterKalender.KalenderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new KalenderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKalender.KalenderViewHolder kalenderViewHolder, int i) {
        String getDate = mListKalender.get(i).getTanggal();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(getDate);
            SimpleDateFormat newFormat = new SimpleDateFormat("dd");
            String dateFix = newFormat.format(date);
            kalenderViewHolder.tvTanggal.setText(dateFix);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        kalenderViewHolder.tvKegiatan.setText(mListKalender.get(i).getNamaKalender());
    }

    @Override
    public int getItemCount() {
        return mListKalender.size();
    }

    public class KalenderViewHolder extends RecyclerView.ViewHolder {
        TextView tvKegiatan,tvTanggal;

        public KalenderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.text_tanggal);

            tvKegiatan = itemView.findViewById(R.id.text_ket);
        }
    }
}
