package com.dscunikom.android.sekolahqu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.AcaraColumns.ID_ACARA;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.AcaraColumns.NAMA_ACARA;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.AcaraColumns.TANGGAL_ACARA;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.BeritaColumns.*;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.PrestasiColumns.ID_PRESTASI;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.PrestasiColumns.NAMA_PRESTASI;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.PrestasiColumns.TANGGAL_DIDAPAT;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.TABLE_ACARA;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.TABLE_BERITA;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.TABLE_PRESTASI;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbsekolahqu";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_BERITA = "create table " + TABLE_BERITA+
            " ("+_ID+ " integer primary key autoincrement, "+
            DESKRIPSI+" text not null, "+
            ID_BERITA+" text not null, "+
            ID_SEKOLAH+" text not null, "+
            IMAGE+" text not null, "+
            NAMA_BERITA+" text not null, "+
            TANGGAL_BERITA+" text not null);";

    private static final String SQL_CREATE_TABLE_ACARA = "create table " + TABLE_ACARA+
            " ("+_ID+ " integer primary key autoincrement, "+
            DatabaseContract.AcaraColumns.DESKRIPSI+" text not null, "+
            ID_ACARA+" text not null, "+
            DatabaseContract.AcaraColumns.ID_SEKOLAH+" text not null, "+
            DatabaseContract.AcaraColumns.IMAGE+" text not null, "+
            NAMA_ACARA+" text not null, "+
            TANGGAL_ACARA+" text not null);";

    private static final String SQL_CREATE_TABLE_PRESTASI = "create table " + TABLE_PRESTASI+
            " ("+_ID+ " integer primary key autoincrement, "+
            DatabaseContract.PrestasiColumns.DESKRIPSI+" text not null, "+
            ID_PRESTASI+" text not null, "+
            DatabaseContract.PrestasiColumns.ID_SEKOLAH+" text not null, "+
            DatabaseContract.PrestasiColumns.IMAGE+" text not null, "+
            NAMA_PRESTASI+" text not null, "+
            TANGGAL_DIDAPAT+" text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BERITA);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ACARA);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PRESTASI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_BERITA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_ACARA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_PRESTASI);
        onCreate(sqLiteDatabase);
    }
}
