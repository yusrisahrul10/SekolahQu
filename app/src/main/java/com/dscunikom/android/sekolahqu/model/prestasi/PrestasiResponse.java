
package com.dscunikom.android.sekolahqu.model.prestasi;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PrestasiResponse {
    @SerializedName("first_data")
    private List<Prestasi> firstData;
    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<Prestasi> mPrestasi;

    public List<Prestasi> getFirstData() {
        return firstData;
    }

    public void setFirstData(List<Prestasi> firstData) {
        this.firstData = firstData;
    }

    public Long getJumlahData() {
        return mJumlahData;
    }

    public void setJumlahData(Long jumlahData) {
        mJumlahData = jumlahData;
    }

    public List<Prestasi> getSpesifikSekolah() {
        return mPrestasi;
    }

    public void setSpesifikSekolah(List<Prestasi> prestasi) {
        mPrestasi = prestasi;
    }

}
