package com.example.serviceapp.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.serviceapp.db.DBHelper;

import java.net.URI;

public class BookContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.serviceapp.book.provider";

    private static final URI CONTENT_BOOK = URI.create("content://" + AUTHORITY + "/book");
    private static final URI CONTENT_USER = URI.create("content://" + AUTHORITY + "/user");

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private SQLiteDatabase mDB;
    private Context mContext;

    static {
        sUriMatcher.addURI(AUTHORITY, "book", 0);
        sUriMatcher.addURI(AUTHORITY, "user", 1);
    }


    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDB = new DBHelper(mContext, DBHelper.DB_NAME, null, 1).getWritableDatabase();
        mDB.execSQL("delete from " + DBHelper.DB_BOOK_TABLE_NAME);
        mDB.execSQL("delete from " + DBHelper.DB_USER_TABLE_NAME);
        mDB.execSQL("insert into book values(1,'book_test001');");
        mDB.execSQL("insert into book values(2,'book_test002');");
        mDB.execSQL("insert into book values(3,'book_test003');");
        mDB.execSQL("insert into user values(1,'user_test001');");
        mDB.execSQL("insert into user values(2,'user_test002');");
        mDB.execSQL("insert into user values(3,'user_test003');");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] project, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("tbq", "query:====================> " + uri.toString());
//        mDB.query(getTableName(uri), strings, s, strings1, null, s1, null, null);
        return mDB.query(getTableName(uri), project, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Log.d("tbq", "insert: ======================>" + uri.toString());
        mDB.insert(getTableName(uri), null, contentValues);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] whereArgs) {
        int n = mDB.delete(getTableName(uri), selection, whereArgs);
        if (n > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return n;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] whereArgs) {
        int n = mDB.update(getTableName(uri), contentValues, selection, whereArgs);
        if (n > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return 0;
    }

    private String getTableName(Uri uri) {
        String tableName = "";
        switch (sUriMatcher.match(uri)) {
            case 0:
                tableName = DBHelper.DB_BOOK_TABLE_NAME;
                break;
            case 1:
                tableName = DBHelper.DB_USER_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
