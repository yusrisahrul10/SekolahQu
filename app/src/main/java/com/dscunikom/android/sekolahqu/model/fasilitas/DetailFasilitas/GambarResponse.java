
package com.dscunikom.android.sekolahqu.model.fasilitas.DetailFasilitas;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class GambarResponse {

    @SerializedName("gambar")
    private List<Gambar> mGambar;

    public List<Gambar> getGambar() {
        return mGambar;
    }

    public void setGambar(List<Gambar> gambar) {
        mGambar = gambar;
    }

}
