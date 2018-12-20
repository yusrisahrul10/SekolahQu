
package com.dscunikom.android.sekolahqu.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PrestasiResponse {

    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<SpesifikSekolah> mSpesifikSekolah;

    public Long getJumlahData() {
        return mJumlahData;
    }

    public void setJumlahData(Long jumlahData) {
        mJumlahData = jumlahData;
    }

    public List<SpesifikSekolah> getSpesifikSekolah() {
        return mSpesifikSekolah;
    }

    public void setSpesifikSekolah(List<SpesifikSekolah> spesifikSekolah) {
        mSpesifikSekolah = spesifikSekolah;
    }

}
