
package com.dscunikom.android.sekolahqu.model.fasilitas;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Fasilitas {

    @SerializedName("id_fasilitas")
    private String mIdFasilitas;
    @SerializedName("id_gambar_fasilitas")
    private String mIdGambarFasilitas;
    @SerializedName("id_sekolah")
    private String mIdSekolah;
    @SerializedName("image")
    private String mImage;
    @SerializedName("nama_fasilitas")
    private String mNamaFasilitas;

    public String getIdFasilitas() {
        return mIdFasilitas;
    }

    public void setIdFasilitas(String idFasilitas) {
        mIdFasilitas = idFasilitas;
    }

    public String getIdGambarFasilitas() {
        return mIdGambarFasilitas;
    }

    public void setIdGambarFasilitas(String idGambarFasilitas) {
        mIdGambarFasilitas = idGambarFasilitas;
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

    public String getNamaFasilitas() {
        return mNamaFasilitas;
    }

    public void setNamaFasilitas(String namaFasilitas) {
        mNamaFasilitas = namaFasilitas;
    }

}
