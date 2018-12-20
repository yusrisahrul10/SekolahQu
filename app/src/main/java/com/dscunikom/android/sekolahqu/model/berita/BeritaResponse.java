
package com.dscunikom.android.sekolahqu.model.berita;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BeritaResponse {

    @SerializedName("first_data")
    private List<SpesifikSekolah> mFirstData;
    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<SpesifikSekolah> mSpesifikSekolah;

    public List<SpesifikSekolah> getmFirstData() {
        return mFirstData;
    }

    public void setmFirstData(List<SpesifikSekolah> mFirstData) {
        this.mFirstData = mFirstData;
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
