
package com.dscunikom.android.sekolahqu.model.sekolah;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class SekolahResponse {

    @SerializedName("result")
    private List<Sekolah> mSekolah;

    public List<Sekolah> getResult() {
        return mSekolah;
    }

    public void setResult(List<Sekolah> sekolah) {
        mSekolah = sekolah;
    }

}
