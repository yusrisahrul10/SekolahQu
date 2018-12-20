package com.dscunikom.android.sekolahqu.network;

import com.dscunikom.android.sekolahqu.model.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.Sekolah;
import com.dscunikom.android.sekolahqu.model.SekolahResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkStores {
    @GET("profile_sekolah")
    Observable<SekolahResponse> getListSekolah();

    @GET("profile_sekolah")
    Observable<Sekolah>getDetailSekolah(@Query("id_sekolah") String id_sekolah);

    @GET("prestasi")
    Observable<PrestasiResponse> getListPrestasi(@Query("id_sekolah") String id_sekolah);

}
