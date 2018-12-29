package com.dscunikom.android.sekolahqu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.dscunikom.android.sekolahqu.model.acara.AcaraModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.AcaraColumns.*;
import static com.dscunikom.android.sekolahqu.database.DatabaseContract.TABLE_ACARA;

public class AcaraHelper {
    private static String DATABASE_TABLE = "TABLE_ACARA";
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public AcaraHelper(Context context) {
        this.context = context;
    }

    public AcaraHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<AcaraModel> queryAcara(String id_sekolah) {
        ArrayList<AcaraModel> arrayList = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_ACARA + " WHERE " + ID_SEKOLAH + " = '"+id_sekolah+"'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        AcaraModel acaraModel;
        if (cursor.getCount() > 0) {
            do {
                acaraModel = new AcaraModel();
                acaraModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                acaraModel.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                acaraModel.setIdAcara(cursor.getString(cursor.getColumnIndexOrThrow(ID_ACARA)));
                acaraModel.setIdSekolah(cursor.getString(cursor.getColumnIndexOrThrow(ID_SEKOLAH)));
                acaraModel.setImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                acaraModel.setNamaAcara(cursor.getString(cursor.getColumnIndexOrThrow(NAMA_ACARA)));
                acaraModel.setTanggalAcara(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL_ACARA)));

                arrayList.add(acaraModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertAcara(AcaraModel acaraModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DESKRIPSI, acaraModel.getDeskripsi());
        initialValues.put(ID_ACARA, acaraModel.getIdAcara());
        initialValues.put(ID_SEKOLAH, acaraModel.getIdSekolah());
        initialValues.put(IMAGE, acaraModel.getImage());
        initialValues.put(NAMA_ACARA, acaraModel.getNamaAcara());
        initialValues.put(TANGGAL_ACARA, acaraModel.getTanggalAcara());
        return database.insert(DATABASE_TABLE, null, initialValues);
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

    public boolean checkIsAcaraAlreadyFavorited(String id) {
        String query = " SELECT * FROM " + TABLE_ACARA + " WHERE " + ID_ACARA + " = '"+id+"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int deleteAcara(String id) {
        return database.delete(TABLE_ACARA, ID_ACARA + " = '"+id+"'", null);
    }
}
