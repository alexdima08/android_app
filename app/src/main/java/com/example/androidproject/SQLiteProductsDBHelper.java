package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

// DEVICE FILE MANAGER -> DATA -> DATA -> com.example.androidproject -> databases

public class SQLiteProductsDBHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "Products.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_1 = "mega_image";
    public static final String TABLE_NAME_2 = "auchan";
    public static final String TABLE_NAME_3 = "carrefour";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "product_name";
    public static final String COLUMN_TYPE = "product_type";
    public static final String COLUMN_PRICE = "product_price";


    public SQLiteProductsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_NAME_1 + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_PRICE + " INTEGER);"; // mega-image
        db.execSQL(query1);
        String query2 = "CREATE TABLE " + TABLE_NAME_2 + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_PRICE + " INTEGER);"; // auchan
        db.execSQL(query2);
        String query3 = "CREATE TABLE " + TABLE_NAME_3 + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_PRICE + " INTEGER);"; // carrefour
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        onCreate(db);

    }

    public void addProduct(String product_name, String product_type, int product_price){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, product_name);
        contentValues.put(COLUMN_TYPE, product_type);
        contentValues.put(COLUMN_PRICE, product_price);

        long result1 = db.insert(TABLE_NAME_1, null, contentValues); //mega-image
        long result2 = db.insert(TABLE_NAME_2, null, contentValues); //auchan
        long result3 = db.insert(TABLE_NAME_3, null, contentValues); //carrefour

        if (result1 == -1 || result2 == -1 || result3 == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllDataMegaImage() {
        String query = "SELECT * FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataAuchan() {
        String query = "SELECT * FROM " + TABLE_NAME_2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public Cursor readAllDataCarrefour() {
        String query = "SELECT * FROM " + TABLE_NAME_3;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
};




