
package com.dscunikom.android.sekolahqu.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Sekolah {

    @SerializedName("alamat")
    private String mAlamat;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("id_sekolah")
    private String mIdSekolah;
    @SerializedName("kalender_akademik")
    private String mKalenderAkademik;
    @SerializedName("logo_sekolah")
    private String mLogoSekolah;
    @SerializedName("nama_sekolah")
    private String mNamaSekolah;
    @SerializedName("no_telp")
    private String mNoTelp;
    @SerializedName("visi_misi")
    private String mVisiMisi;

    public String getAlamat() {
        return mAlamat;
    }

    public void setAlamat(String alamat) {
        mAlamat = alamat;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getIdSekolah() {
        return mIdSekolah;
    }

    public void setIdSekolah(String idSekolah) {
        mIdSekolah = idSekolah;
    }

    public String getKalenderAkademik() {
        return mKalenderAkademik;
    }

    public void setKalenderAkademik(String kalenderAkademik) {
        mKalenderAkademik = kalenderAkademik;
    }

    public String getLogoSekolah() {
        return mLogoSekolah;
    }

    public void setLogoSekolah(String logoSekolah) {
        mLogoSekolah = logoSekolah;
    }

    public String getNamaSekolah() {
        return mNamaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        mNamaSekolah = namaSekolah;
    }

    public String getNoTelp() {
        return mNoTelp;
    }

    public void setNoTelp(String noTelp) {
        mNoTelp = noTelp;
    }

    public String getVisiMisi() {
        return mVisiMisi;
    }

    public void setVisiMisi(String visiMisi) {
        mVisiMisi = visiMisi;
    }

}
