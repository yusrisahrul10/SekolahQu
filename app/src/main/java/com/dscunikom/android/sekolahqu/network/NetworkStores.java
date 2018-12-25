package com.dscunikom.android.sekolahqu.network;

import com.dscunikom.android.sekolahqu.model.ekskul.EkskulResponse;
import com.dscunikom.android.sekolahqu.model.fasilitas.FasilitasResponse;
import com.dscunikom.android.sekolahqu.model.acara.AcaraResponse;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;
import com.dscunikom.android.sekolahqu.model.berita.BeritaResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.PrestasiResponse;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;
import com.dscunikom.android.sekolahqu.model.sekolah.Sekolah;
import com.dscunikom.android.sekolahqu.model.sekolah.SekolahResponse;
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
    
    @GET("prestasi")
    Observable<Prestasi> getDetailPrestasi(@Query("id_prestasi") String id_prestasi);


    @GET("berita")
    Observable<BeritaResponse> getListBerita(@Query("id_sekolah") String id_sekolah);

    @GET("berita")
    Observable<BeritaModel> getDetailBerita(@Query("id_berita") String id_berita);

    @GET("acara")
    Observable<AcaraResponse> getListAcara(@Query("id_sekolah") String id_sekolah);

    @GET("acara")
    Observable<AcaraModel> getDetailAcara(@Query("id_acara") String id_acara);

    @GET("fasilitas")
    Observable<FasilitasResponse> getFasilitasSekolah(@Query("id_sekolah") String id_sekolah);

    @GET("ekskul")
    Observable<EkskulResponse> getEkskulSekolah(@Query("id_sekolah") String id_sekolah);
}
