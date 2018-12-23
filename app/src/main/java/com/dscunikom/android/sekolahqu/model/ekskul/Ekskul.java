
package com.dscunikom.android.sekolahqu.model.ekskul;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Ekskul {

    @SerializedName("deskripsi")
    private String mDeskripsi;
    @SerializedName("id_ekskul")
    private String mIdEkskul;
    @SerializedName("id_sekolah")
    private String mIdSekolah;
    @SerializedName("image")
    private String mImage;
    @SerializedName("ketua")
    private String mKetua;
    @SerializedName("nama_ekskul")
    private String mNamaEkskul;
    @SerializedName("pembina")
    private String mPembina;

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        mDeskripsi = deskripsi;
    }

    public String getIdEkskul() {
        return mIdEkskul;
    }

    public void setIdEkskul(String idEkskul) {
        mIdEkskul = idEkskul;
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

    public String getKetua() {
        return mKetua;
    }

    public void setKetua(String ketua) {
        mKetua = ketua;
    }

    public String getNamaEkskul() {
        return mNamaEkskul;
    }

    public void setNamaEkskul(String namaEkskul) {
        mNamaEkskul = namaEkskul;
    }

    public String getPembina() {
        return mPembina;
    }

    public void setPembina(String pembina) {
        mPembina = pembina;
    }

}
