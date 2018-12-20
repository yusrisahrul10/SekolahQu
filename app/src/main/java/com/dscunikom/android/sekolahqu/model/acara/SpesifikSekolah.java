
package com.dscunikom.android.sekolahqu.model.acara;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SpesifikSekolah {

    @SerializedName("deskripsi")
    private String mDeskripsi;
    @SerializedName("id_acara")
    private String mIdAcara;
    @SerializedName("id_sekolah")
    private String mIdSekolah;
    @SerializedName("image")
    private String mImage;
    @SerializedName("nama_acara")
    private String mNamaAcara;
    @SerializedName("tanggal_acara")
    private String mTanggalAcara;

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        mDeskripsi = deskripsi;
    }

    public String getIdAcara() {
        return mIdAcara;
    }

    public void setIdAcara(String idAcara) {
        mIdAcara = idAcara;
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

    public String getNamaAcara() {
        return mNamaAcara;
    }

    public void setNamaAcara(String namaAcara) {
        mNamaAcara = namaAcara;
    }

    public String getTanggalAcara() {
        return mTanggalAcara;
    }

    public void setTanggalAcara(String tanggalAcara) {
        mTanggalAcara = tanggalAcara;
    }

}
