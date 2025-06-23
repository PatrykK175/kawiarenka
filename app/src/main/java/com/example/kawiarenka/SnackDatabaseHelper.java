package com.example.kawiarenka;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class SnackDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cafeteria.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SNACKS = "snacks";

    public SnackDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SNACKS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "description TEXT," +
                "price REAL)";
        db.execSQL(CREATE_TABLE);

        insertSampleSnacks(db);
    }

    private void insertSampleSnacks(SQLiteDatabase db) {
        insertSnack(db, "Chipsy", "Ziemniaczane z solą morską", 4.99);
        insertSnack(db, "Czekolada", "Mleczna 100g", 5.50);
        insertSnack(db, "Orzeszki", "Solone 200g", 6.25);
        insertSnack(db, "Baton energetyczny", "Z orzechami i miodem", 3.80);
        insertSnack(db, "Krakersy", "Z serem cheddar", 4.20);
    }

    private void insertSnack(SQLiteDatabase db, String name, String description, double price) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("price", price);
        db.insert(TABLE_SNACKS, null, values);
    }

    public List<Snack> getAllSnacks() {
        List<Snack> snacks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SNACKS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                snacks.add(new Snack(
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return snacks;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SNACKS);
        onCreate(db);
    }
}