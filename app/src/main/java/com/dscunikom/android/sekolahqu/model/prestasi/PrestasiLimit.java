
package com.dscunikom.android.sekolahqu.model.prestasi;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PrestasiLimit {

    @SerializedName("result")
    private List<Prestasi> mResult;

    public List<Prestasi> getResult() {
        return mResult;
    }

    public void setResult(List<Prestasi> result) {
        mResult = result;
    }

}
