
package com.dscunikom.android.sekolahqu.model.prestasi;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PrestasiResponse {
    @SerializedName("first_data")
    private List<SpesifikSekolah> firstData;
    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<SpesifikSekolah> mSpesifikSekolah;

    public List<SpesifikSekolah> getFirstData() {
        return firstData;
    }

    public void setFirstData(List<SpesifikSekolah> firstData) {
        this.firstData = firstData;
    }

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
