
package com.dscunikom.android.sekolahqu.model.prestasi;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SpesifikSekolah {

    private int id;

    @SerializedName("deskripsi")
    private String mDeskripsi;
    @SerializedName("id_prestasi")
    private String mIdPrestasi;
    @SerializedName("id_sekolah")
    private String mIdSekolah;
    @SerializedName("image")
    private String mImage;
    @SerializedName("nama_prestasi")
    private String mNamaPrestasi;
    @SerializedName("tanggal_didapat")
    private String mTanggalDidapat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        mDeskripsi = deskripsi;
    }

    public String getIdPrestasi() {
        return mIdPrestasi;
    }

    public void setIdPrestasi(String idPrestasi) {
        mIdPrestasi = idPrestasi;
    }

    public String getIdSekolah() {
        return mIdSekolah;
    }

    public void setIdSekolah(String idSekolah) {
        mIdSekolah = idSekolah;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getNamaPrestasi() {
        return mNamaPrestasi;
    }

    public void setNamaPrestasi(String namaPrestasi) {
        mNamaPrestasi = namaPrestasi;
    }

    public String getTanggalDidapat() {
        return mTanggalDidapat;
    }

    public void setTanggalDidapat(String tanggalDidapat) {
        mTanggalDidapat = tanggalDidapat;
    }

}
