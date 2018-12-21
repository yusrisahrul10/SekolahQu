
package com.dscunikom.android.sekolahqu.model.berita;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class BeritaResponse {

    @SerializedName("first_data")
    private List<BeritaModel> mFirstData;
    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<BeritaModel> mBeritaModel;

    public List<BeritaModel> getmFirstData() {
        return mFirstData;
    }

    public void setmFirstData(List<BeritaModel> mFirstData) {
        this.mFirstData = mFirstData;
    }

    public Long getJumlahData() {
        return mJumlahData;
    }

    public void setJumlahData(Long jumlahData) {
        mJumlahData = jumlahData;
    }

    public List<BeritaModel> getSpesifikSekolah() {
        return mBeritaModel;
    }

    public void setSpesifikSekolah(List<BeritaModel> beritaModel) {
        mBeritaModel = beritaModel;
    }

}
