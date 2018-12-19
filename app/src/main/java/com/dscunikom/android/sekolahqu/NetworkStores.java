package com.dscunikom.android.sekolahqu;

import com.dscunikom.android.sekolahqu.model.SekolahResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkStores {
    @GET("profile_sekolah")
    Observable<SekolahResponse> getListSekolah();
}
