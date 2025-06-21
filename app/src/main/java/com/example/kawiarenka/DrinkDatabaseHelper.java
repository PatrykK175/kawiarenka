package com.example.kawiarenka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DrinkDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "kawiarenka.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DRINKS = "drinks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";

    public DrinkDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_DRINKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRICE + " REAL)";
        db.execSQL(createTable);

        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        insertDrink(db, "Kawa Latte", "Delikatna kawa z mlekiem", 12.99);
        insertDrink(db, "Herbata Zielona", "Zdrowa herbata z liści zielonej herbaty", 8.50);
        insertDrink(db, "Sok Pomarańczowy", "Świeżo wyciskany sok z pomarańczy", 10.00);
        insertDrink(db, "Czekolada na Gorąco", "Gęsta czekolada z bitą śmietaną", 14.50);
        insertDrink(db, "Woda Mineralna", "Naturalna woda źródlana", 5.00);
    }

    private void insertDrink(SQLiteDatabase db, String name, String description, double price) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRICE, price);
        db.insert(TABLE_DRINKS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS);
        onCreate(db);
    }

    public List<DrinkModel> getAllDrinks() {
        List<DrinkModel> drinks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DRINKS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE));

                drinks.add(new DrinkModel(id, name, description, price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return drinks;
    }
}