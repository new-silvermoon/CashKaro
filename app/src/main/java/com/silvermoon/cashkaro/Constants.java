package com.silvermoon.cashkaro;

import android.content.ContentUris;
import android.net.Uri;

import com.silvermoon.data.DatabaseContract;



public class Constants {


    public static final String[] URL_VALUES ={
            "http://www.flipkart.com/",
            "http://www.jabong.com/",
            "http://www.amazon.in/",
            "http://www.shopclues.com/",
            "http://www.nykaa.com/",
            "http://www.myntra.com/"
    };

    public static final String[] STORE_HEADING ={
            "Top Flipkart Offers",
            "Top Jabong Offers",
            "Top Amazon Offers",
            "Top ShopClues Offers",
            "Top Nyka Offers",
            "Top Myntra Offers",

    };

    public static final Uri [] STORE_URI ={
            DatabaseContract.FLIPKART_DEALS_CONTENT_URI,
            DatabaseContract.JABONG_DEALS_CONTENT_URI,
            DatabaseContract.AMAZON_DEALS_CONTENT_URI,
            DatabaseContract.SHOPCLUES_DEALS_CONTENT_URI,
            DatabaseContract.NYKA_DEALS_CONTENT_URI,
            DatabaseContract.MYNTRA_DEALS_CONTENT_URI
    };
}
