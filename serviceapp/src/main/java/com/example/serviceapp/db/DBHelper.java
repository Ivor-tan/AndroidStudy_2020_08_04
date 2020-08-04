package com.example.serviceapp.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "book_provider.db";
    public static final String DB_BOOK_TABLE_NAME = "book";
    public static final String DB_USER_TABLE_NAME = "user";
    private static final int DB_VERSION = 1;

    //sql commend
    private static final String SQL_CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " + DB_BOOK_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT)";
    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + DB_USER_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT)";


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, DB_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_BOOK_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
