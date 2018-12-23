
package com.dscunikom.android.sekolahqu.model.fasilitas;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class FasilitasResponse {

    @SerializedName("result")
    private List<Fasilitas> mFasilitas;

    public List<Fasilitas> getResult() {
        return mFasilitas;
    }

    public void setResult(List<Fasilitas> fasilitas) {
        mFasilitas = fasilitas;
    }

}
