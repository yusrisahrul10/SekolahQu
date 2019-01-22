package com.dscunikom.android.sekolahqu.model.kalender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kalender {
    @SerializedName("id_kalender")
    @Expose
    private String idKalender;
    @SerializedName("nama_kalender")
    @Expose
    private String namaKalender;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("nama_bulan")
    @Expose
    private String namaBulan;
    @SerializedName("id_sekolah")
    @Expose
    private String id_sekolah;

    public String getIdKalender() {
        return idKalender;
    }

    public void setIdKalender(String idKalender) {
        this.idKalender = idKalender;
    }

    public String getNamaKalender() {
        return namaKalender;
    }

    public void setNamaKalender(String namaKalender) {
        this.namaKalender = namaKalender;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaBulan() {
        return namaBulan;
    }

    public void setNamaBulan(String namaBulan) {
        this.namaBulan = namaBulan;
    }

    public String getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(String id_sekolah) {
        this.id_sekolah = id_sekolah;
    }
}
