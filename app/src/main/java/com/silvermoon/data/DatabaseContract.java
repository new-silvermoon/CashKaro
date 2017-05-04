package com.silvermoon.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Provides all the necessary information related to table names, columns and authorities
 */

public class DatabaseContract {
    //Database schema information


    public static final String TABLE_FLIPKART_DEALS = "flipkartdeals";
    public static final String TABLE_AMAZON_DEALS = "amazondeals";
    public static final String TABLE_MYNTRA_DEALS = "myntradeals";
    public static final String TABLE_JABONG_DEALS = "jabongdeals";
    public static final String TABLE_NYKA_DEALS = "nykadeals";
    public static final String TABLE_SHOPCLUES_DEALS="shopcluesdeals";



    public static final class StoreDealsColumns implements BaseColumns{
        //Deal heading
        public static final String DEAL_HEADING ="deal_heading";
        //Deal description
        public static final String DEAL_DESCRIPTION="deal_description";
        //Additional info for the deal
        public static final String ADDITIONAL_INFO="add_info";
        //Stores information whether the deal has been marked favourite by the user or not
        public static final String IS_FAVOURITE="is_favourite";
        //Url for the deal
        public static final String STORE_URL="store_url";
    }

    //Unique authority string for the content provider
    public static final String CONTENT_AUTHORITY = "com.silvermoon";

    //Base content URIs for accessing providers

    public static final Uri FLIPKART_DEALS_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY).appendPath(TABLE_FLIPKART_DEALS).build();
    public static final Uri AMAZON_DEALS_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY).appendPath(TABLE_AMAZON_DEALS).build();
    public static final Uri MYNTRA_DEALS_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY).appendPath(TABLE_MYNTRA_DEALS).build();
    public static final Uri NYKA_DEALS_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY).appendPath(TABLE_NYKA_DEALS).build();
    public static final Uri JABONG_DEALS_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY).appendPath(TABLE_JABONG_DEALS).build();
    public static final Uri SHOPCLUES_DEALS_CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY).appendPath(TABLE_SHOPCLUES_DEALS).build();


    //Sort deals by latest first

    public static final String STORE_DEALS_SORT_ORDER = String.format("%s DESC",StoreDealsColumns._ID);

    /* Helpers to retrieve column values */
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }

}
