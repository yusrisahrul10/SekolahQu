
package com.dscunikom.android.sekolahqu.model.acara;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AcaraResponse {

    @SerializedName("first_data")
    private List<AcaraModel> mFirstData;
    @SerializedName("jumlah_data")
    private Long mJumlahData;
    @SerializedName("spesifik_sekolah")
    private List<AcaraModel> mAcaraModel;

    public List<AcaraModel> getmFirstData() {
        return mFirstData;
    }

    public void setmFirstData(List<AcaraModel> mFirstData) {
        this.mFirstData = mFirstData;
    }

    public Long getJumlahData() {
        return mJumlahData;
    }

    public void setJumlahData(Long jumlahData) {
        mJumlahData = jumlahData;
    }

    public List<AcaraModel> getSpesifikSekolah() {
        return mAcaraModel;
    }

    public void setSpesifikSekolah(List<AcaraModel> acaraModel) {
        mAcaraModel = acaraModel;
    }

}
