
package com.dscunikom.android.sekolahqu.model.ekskul;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class EkskulResponse {

    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<Ekskul> mEkskul;

    public Long getJumlahData() {
        return mJumlahData;
    }

    public void setJumlahData(Long jumlahData) {
        mJumlahData = jumlahData;
    }

    public List<Ekskul> getSpesifikSekolah() {
        return mEkskul;
    }

    public void setSpesifikSekolah(List<Ekskul> ekskul) {
        mEkskul = ekskul;
    }

}
