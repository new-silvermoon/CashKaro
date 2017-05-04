package com.silvermoon.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Content provider for deals. provides Uri matchers and methods
 * to perform query,insert,update and delete operations in the database
 */

public class DealsProvider extends ContentProvider {

    private static final String TAG = DealsProvider.class.getSimpleName();


    private static final int FLIPKART_DEALS = 101;
    private static final int FLIPKART_DEALS_WITH_ID =1011;
    private static final int AMAZON_DEALS = 102;
    private static final int AMAZON_DEALS_WITH_ID=1021;
    private static final int MYNTRA_DEALS = 103;
    private static final int MYNTRA_DEALS_WITH_ID=1031;
    private static final int NYKA_DEALS = 104;
    private static final int NYKA_DEALS_WITH_ID=1041;
    private static final int JABONG_DEALS = 105;
    private static final int JABONG_DEALS_WITH_ID=1051;
    private static final int SHOPCLUES_DEALS = 106;
    private static final int SHOPCLUES_DEALS_WITH_ID=1061;

    private DbHelper dbHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_FLIPKART_DEALS,
                FLIPKART_DEALS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_FLIPKART_DEALS + "/#",
                FLIPKART_DEALS_WITH_ID);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_AMAZON_DEALS,
                AMAZON_DEALS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_AMAZON_DEALS + "/#",
                AMAZON_DEALS_WITH_ID);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_MYNTRA_DEALS,
                MYNTRA_DEALS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_MYNTRA_DEALS + "/#",
                MYNTRA_DEALS_WITH_ID);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_JABONG_DEALS,
                JABONG_DEALS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_JABONG_DEALS + "/#",
                JABONG_DEALS_WITH_ID);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_NYKA_DEALS,
                NYKA_DEALS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_NYKA_DEALS + "/#",
                NYKA_DEALS_WITH_ID);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_SHOPCLUES_DEALS,
                SHOPCLUES_DEALS);
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_SHOPCLUES_DEALS + "/#",
                SHOPCLUES_DEALS_WITH_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        selection = null;
        selectionArgs = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch(sUriMatcher.match(uri)){

            case FLIPKART_DEALS:
                cursor = db.query(DatabaseContract.TABLE_FLIPKART_DEALS,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case AMAZON_DEALS:
                cursor = db.query(DatabaseContract.TABLE_AMAZON_DEALS,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case MYNTRA_DEALS:
                cursor = db.query(DatabaseContract.TABLE_MYNTRA_DEALS,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case NYKA_DEALS:
                cursor = db.query(DatabaseContract.TABLE_NYKA_DEALS,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case JABONG_DEALS:
                cursor = db.query(DatabaseContract.TABLE_JABONG_DEALS,null,selection,selectionArgs,null,null,sortOrder);
                break;
            case SHOPCLUES_DEALS:
                cursor = db.query(DatabaseContract.TABLE_SHOPCLUES_DEALS,null,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                Log.i(TAG, "query: method. Not a valid URI");
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }


    /*
    * Insert operation is not used in this shell app.
    * But can be used in the production app to fetch data from Webservices and insert
    * into Database*/
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /*Not used in this shell app*/
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int returnValue=0;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = String.format("%s = ?",DatabaseContract.StoreDealsColumns._ID);
        String [] whereArgs = selectionArgs;
        switch(sUriMatcher.match(uri)){
            case FLIPKART_DEALS_WITH_ID:
                returnValue = db.update(DatabaseContract.TABLE_FLIPKART_DEALS,values,whereClause,whereArgs);
                break;
            case AMAZON_DEALS_WITH_ID:
                returnValue = db.update(DatabaseContract.TABLE_AMAZON_DEALS,values,whereClause,whereArgs);
                break;
            case MYNTRA_DEALS_WITH_ID:
                returnValue = db.update(DatabaseContract.TABLE_MYNTRA_DEALS,values,whereClause,whereArgs);
                break;
            case JABONG_DEALS_WITH_ID:
                returnValue = db.update(DatabaseContract.TABLE_JABONG_DEALS,values,whereClause,whereArgs);
                break;
            case NYKA_DEALS_WITH_ID:
                returnValue = db.update(DatabaseContract.TABLE_NYKA_DEALS,values,whereClause,whereArgs);
                break;
            case SHOPCLUES_DEALS_WITH_ID:
                returnValue = db.update(DatabaseContract.TABLE_SHOPCLUES_DEALS,values,whereClause,whereArgs);
                break;
        }

        return returnValue;
    }
}
