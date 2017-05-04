package com.silvermoon.data;

import android.database.Cursor;

/**
 * Helpful data model for holding attributes related to a deal.
 */

public class StoreDeals {

    public static final long NO_ID = -1;

    //Unique identifier in database
    public long id;
    //Deal heading
    public final String dealHeading;
    //Deal Description
    public final String dealDesciption;
    //Deal Additional Info
    public final String additionalInfo;
    //Deal favorite info
    public final Boolean isFav;
    //Store Url
    public final String storeUrl;


    /**
     * Create a new deal from discrete items
     */
    public StoreDeals(String dealHeading,String dealDescription,String additionalInfo,boolean isFav,String storeUrl){

        this.id=NO_ID;
        this.dealHeading=dealHeading;
        this.dealDesciption=dealDescription;
        this.additionalInfo=additionalInfo;
        this.isFav=isFav;
        this.storeUrl=storeUrl;
    }

    /**
     * Create a new deal from a database Cursor
     */
    public StoreDeals(Cursor cursor){
        this.id = DatabaseContract.getColumnLong(cursor, DatabaseContract.StoreDealsColumns._ID);
        this.dealHeading=DatabaseContract.getColumnString(cursor, DatabaseContract.StoreDealsColumns.DEAL_HEADING);
        this.dealDesciption=DatabaseContract.getColumnString(cursor, DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION);
        this.additionalInfo=DatabaseContract.getColumnString(cursor, DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO);
        this.isFav=DatabaseContract.getColumnInt(cursor, DatabaseContract.StoreDealsColumns.IS_FAVOURITE)==1;
        this.storeUrl=DatabaseContract.getColumnString(cursor,DatabaseContract.StoreDealsColumns.STORE_URL);
    }
}
