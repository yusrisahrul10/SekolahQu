package com.dscunikom.android.sekolahqu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.dscunikom.android.sekolahqu.model.berita.BeritaModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.BeritaColumns.*;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.TABLE_BERITA;

public class BeritaHelper {
    private static String DATABASE_TABLE = "TABLE_BERITA";
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public BeritaHelper(Context context) {
        this.context = context;
    }

    public BeritaHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<BeritaModel> queryBerita(String id_sekolah){
        ArrayList<BeritaModel> arrayList = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_BERITA + " WHERE " + ID_SEKOLAH + " = '" + id_sekolah + "'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        BeritaModel beritaModel;
        if (cursor.getCount() > 0) {
            do {
                beritaModel = new BeritaModel();
                beritaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                beritaModel.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                beritaModel.setIdBerita(cursor.getString(cursor.getColumnIndexOrThrow(ID_BERITA)));
                beritaModel.setIdSekolah(cursor.getString(cursor.getColumnIndexOrThrow(ID_SEKOLAH)));
                beritaModel.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                beritaModel.setNamaBerita(cursor.getString(cursor.getColumnIndexOrThrow(NAMA_BERITA)));
                beritaModel.setTanggalBerita(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL_BERITA)));

                arrayList.add(beritaModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertBerita(BeritaModel beritaModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DESKRIPSI, beritaModel.getDeskripsi());
        initialValues.put(ID_BERITA, beritaModel.getIdBerita());
        initialValues.put(ID_SEKOLAH, beritaModel.getIdSekolah());
        initialValues.put(IMAGE, beritaModel.getImage());
        initialValues.put(NAMA_BERITA, beritaModel.getNamaBerita());
        initialValues.put(TANGGAL_BERITA, beritaModel.getTanggalBerita());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean checkIsBeritaAlreadyFavorited(String id) {
        String query = " SELECT * FROM " + TABLE_BERITA + " WHERE " + ID_BERITA + " = '"+id+"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int deleteBerita(String id) {
        return database.delete(TABLE_BERITA, ID_BERITA + " = '"+id+"'", null);
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
}
