package com.example.kawiarenka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CafeLocationDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME  = "cafes.db";
    private static final int    DATABASE_VERSION = 2;

    public static final String TABLE_CAFE   = "CafeLocations";
    public static final String COL_ID       = "id";
    public static final String COL_NAME     = "name";
    public static final String COL_ADDRESS  = "address";
    public static final String COL_HOURS    = "opening_hours";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_CAFE + " (" +
                    COL_ID      + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME    + " TEXT NOT NULL, " +
                    COL_ADDRESS + " TEXT NOT NULL, " +
                    COL_HOURS   + " TEXT NOT NULL" +
                    ");";

    public CafeLocationDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,    "Cafe Belg");
        cv.put(COL_ADDRESS, "Aleja Najświętszej Maryi Panny 32, Częstochowa");
        cv.put(COL_HOURS,   "Pon–Pt 8:00–20:00, Sob–Nd 9:00–18:00");
        db.insert(TABLE_CAFE, null, cv);

        cv.clear();
        cv.put(COL_NAME,    "Sweet Home Cafe");
        cv.put(COL_ADDRESS, "ul. 7 Kamienic 11, Częstochowa");
        cv.put(COL_HOURS,   "Pon–Pt 7:00–19:00, Sob 8:00–15:00");
        db.insert(TABLE_CAFE, null, cv);

        cv.clear();
        cv.put(COL_NAME,    "Szafran Cukiernia");
        cv.put(COL_ADDRESS, "ul. Wręczycka 20, Częstochowa ");
        cv.put(COL_HOURS,   "Codziennie 8:00–19:00");
        db.insert(TABLE_CAFE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAFE);
        onCreate(db);
    }

    public List<CafeLocation> getAllCafeLocations() {
        List<CafeLocation> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_CAFE,
                new String[]{COL_ID, COL_NAME, COL_ADDRESS, COL_HOURS},
                null, null, null, null, null
        );
        if (cursor.moveToFirst()) {
            do {
                int    id      = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String name    = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS));
                String hours   = cursor.getString(cursor.getColumnIndexOrThrow(COL_HOURS));
                list.add(new CafeLocation(id, name, address, hours));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
