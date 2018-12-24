
package com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Gambar {

    @SerializedName("id_fasilitas")
    private String mIdFasilitas;
    @SerializedName("id_gambar_fasilitas")
    private String mIdGambarFasilitas;
    @SerializedName("image")
    private String mImage;

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

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

}
