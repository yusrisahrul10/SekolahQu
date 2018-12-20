
package com.dscunikom.android.sekolahqu.model.berita;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SpesifikSekolah {

    @SerializedName("deskripsi")
    private String mDeskripsi;
    @SerializedName("id_berita")
    private String mIdBerita;
    @SerializedName("id_sekolah")
    private String mIdSekolah;
    @SerializedName("image")
    private String mImage;
    @SerializedName("nama_berita")
    private String mNamaBerita;
    @SerializedName("tanggal_berita")
    private String mTanggalBerita;

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        mDeskripsi = deskripsi;
    }

    public String getIdBerita() {
        return mIdBerita;
    }

    public void setIdBerita(String idBerita) {
        mIdBerita = idBerita;
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

    public String getNamaBerita() {
        return mNamaBerita;
    }

    public void setNamaBerita(String namaBerita) {
        mNamaBerita = namaBerita;
    }

    public String getTanggalBerita() {
        return mTanggalBerita;
    }

    public void setTanggalBerita(String tanggalBerita) {
        mTanggalBerita = tanggalBerita;
    }

}
