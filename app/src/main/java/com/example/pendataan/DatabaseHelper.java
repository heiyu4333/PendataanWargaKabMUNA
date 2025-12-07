package com.example.pendataan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "pendataan.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_KK = "kk_table";
    public static final String TABLE_ANGGOTA = "anggota_table";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_KK + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nomor_kk TEXT, alamat TEXT, kondisi_rumah TEXT, kelurahan TEXT, kecamatan TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_ANGGOTA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, kk_id INTEGER, nik TEXT, nama TEXT, tanggal_lahir TEXT, hubungan TEXT, kesehatan TEXT, pendidikan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANGGOTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KK);
        onCreate(db);
    }

    public long insertKK(KK kk) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomor_kk", kk.getNomorKK());
        cv.put("alamat", kk.getAlamat());
        cv.put("kondisi_rumah", kk.getKondisiRumah());
        cv.put("kelurahan", kk.getKelurahan());
        cv.put("kecamatan", kk.getKecamatan());
        return db.insert(TABLE_KK, null, cv);
    }

    public long insertAnggota(Anggota a) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kk_id", a.getKkId());
        cv.put("nik", a.getNik());
        cv.put("nama", a.getNama());
        cv.put("tanggal_lahir", a.getTanggalLahir());
        cv.put("hubungan", a.getHubungan());
        cv.put("kesehatan", a.getKesehatan());
        cv.put("pendidikan", a.getPendidikan());
        return db.insert(TABLE_ANGGOTA, null, cv);
    }

    public List<KK> getAllKK() {
        List<KK> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nomor_kk, alamat, kondisi_rumah, kelurahan, kecamatan FROM " + TABLE_KK + " ORDER BY id DESC", null);
        if (c != null) {
            while (c.moveToNext()) {
                KK kk = new KK(
                        c.getLong(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)
                );
                list.add(kk);
            }
            c.close();
        }
        return list;
    }

    public List<Anggota> getAllAnggota() {
        List<Anggota> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, kk_id, nik, nama, tanggal_lahir, hubungan, kesehatan, pendidikan FROM " + TABLE_ANGGOTA + " ORDER BY id DESC", null);
        if (c != null) {
            while (c.moveToNext()) {
                Anggota a = new Anggota(
                        c.getLong(0),
                        c.getLong(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7)
                );
                list.add(a);
            }
            c.close();
        }
        return list;
    }
}
