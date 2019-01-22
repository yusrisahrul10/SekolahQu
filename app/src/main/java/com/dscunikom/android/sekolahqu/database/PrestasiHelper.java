package com.dscunikom.android.sekolahqu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.dscunikom.android.sekolahqu.model.prestasi.Prestasi;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.PrestasiColumns.*;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.TABLE_PRESTASI;

public class PrestasiHelper {
    private static String DATABASE_TABLE = "TABLE_PRESTASI";
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public PrestasiHelper(Context context) {
        this.context = context;
    }

    public PrestasiHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Prestasi> queryPrestasi(String id_sekolah) {
        ArrayList<Prestasi> arrayList = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_PRESTASI + " WHERE " +ID_SEKOLAH + " = '"+id_sekolah+"'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        Prestasi spesifikSekolah;
        if (cursor.getCount() > 0) {
            do {
                spesifikSekolah = new Prestasi();
                spesifikSekolah.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                spesifikSekolah.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                spesifikSekolah.setIdPrestasi(cursor.getString(cursor.getColumnIndexOrThrow(ID_PRESTASI)));
                spesifikSekolah.setIdSekolah(cursor.getString(cursor.getColumnIndexOrThrow(ID_SEKOLAH)));
                spesifikSekolah.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                spesifikSekolah.setNamaPrestasi(cursor.getString(cursor.getColumnIndexOrThrow(NAMA_PRESTASI)));
                spesifikSekolah.setTanggalDidapat(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL_DIDAPAT)));

                arrayList.add(spesifikSekolah);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public long insertPrestasi(Prestasi spesifikSekolah) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DESKRIPSI, spesifikSekolah.getDeskripsi());
        initialValues.put(ID_PRESTASI, spesifikSekolah.getIdPrestasi());
        initialValues.put(ID_SEKOLAH, spesifikSekolah.getIdSekolah());
        initialValues.put(IMAGE, spesifikSekolah.getImage());
        initialValues.put(NAMA_PRESTASI, spesifikSekolah.getNamaPrestasi());
        initialValues.put(TANGGAL_DIDAPAT, spesifikSekolah.getTanggalDidapat());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean checkIsPrestasiAlreadyFavorited(String id){
        String query = " SELECT * FROM " + TABLE_PRESTASI + " WHERE " + ID_PRESTASI + " = '"+id+"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int deletePrestasi(String id) {
        return database.delete(TABLE_PRESTASI, ID_PRESTASI + " = '"+id+"'", null);
    }
}
