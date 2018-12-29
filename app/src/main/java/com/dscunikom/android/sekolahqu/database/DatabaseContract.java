package com.dscunikom.android.sekolahqu.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_BERITA = "table_berita";
    public static String TABLE_ACARA = "table_acara";
    public static String TABLE_PRESTASI = "table_prestasi";

    public static final class BeritaColumns implements BaseColumns {
        public static String DESKRIPSI = "deskripsi";
        public static String ID_BERITA = "id_berita";
        public static String ID_SEKOLAH = "id_sekolah";
        public static String IMAGE = "image";
        public static String NAMA_BERITA = "nama_berita";
        public static String TANGGAL_BERITA = "tanggal_berita";
    }

    public static final class AcaraColumns implements BaseColumns {
        public static String DESKRIPSI = "deskripsi";
        public static String ID_ACARA = "id_acara";
        public static String ID_SEKOLAH = "id_sekolah";
        public static String IMAGE = "image";
        public static String NAMA_ACARA = "nama_acara";
        public static String TANGGAL_ACARA = "tanggal_acara";
    }

    public static final class PrestasiColumns implements BaseColumns {
        public static String DESKRIPSI = "deskripsi";
        public static String ID_PRESTASI = "id_prestasi";
        public static String ID_SEKOLAH = "id_sekolah";
        public static String IMAGE = "image";
        public static String NAMA_PRESTASI = "nama_prestasi";
        public static String TANGGAL_DIDAPAT = "tanggal_didapat";
    }
}
