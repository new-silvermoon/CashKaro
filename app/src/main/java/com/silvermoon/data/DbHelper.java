package com.silvermoon.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * SQLite database helper class to create tables and insert default values to it
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="CashKaroDb.db";
    private static final int DATABASE_VERSION = 1;

    //table creation scripts


    private static final String SQL_CREATE_TABLE_FLIPKART_DEALS = String.format("create table %s"
                    +" (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text)",
            DatabaseContract.TABLE_FLIPKART_DEALS,
            DatabaseContract.StoreDealsColumns._ID,
            DatabaseContract.StoreDealsColumns.DEAL_HEADING,
            DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,
            DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,
            DatabaseContract.StoreDealsColumns.IS_FAVOURITE,
            DatabaseContract.StoreDealsColumns.STORE_URL);

    private static final String SQL_CREATE_TABLE_AMAZON_DEALS = String.format("create table %s"
                    +" (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text)",
            DatabaseContract.TABLE_AMAZON_DEALS,
            DatabaseContract.StoreDealsColumns._ID,
            DatabaseContract.StoreDealsColumns.DEAL_HEADING,
            DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,
            DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,
            DatabaseContract.StoreDealsColumns.IS_FAVOURITE,
            DatabaseContract.StoreDealsColumns.STORE_URL);

    private static final String SQL_CREATE_TABLE_JABONG_DEALS = String.format("create table %s"
                    +" (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text)",
            DatabaseContract.TABLE_JABONG_DEALS,
            DatabaseContract.StoreDealsColumns._ID,
            DatabaseContract.StoreDealsColumns.DEAL_HEADING,
            DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,
            DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,
            DatabaseContract.StoreDealsColumns.IS_FAVOURITE,
            DatabaseContract.StoreDealsColumns.STORE_URL);

    private static final String SQL_CREATE_TABLE_MYNTRA_DEALS = String.format("create table %s"
                    +" (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text)",
            DatabaseContract.TABLE_MYNTRA_DEALS,
            DatabaseContract.StoreDealsColumns._ID,
            DatabaseContract.StoreDealsColumns.DEAL_HEADING,
            DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,
            DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,
            DatabaseContract.StoreDealsColumns.IS_FAVOURITE,
            DatabaseContract.StoreDealsColumns.STORE_URL);

    private static final String SQL_CREATE_TABLE_NYKA_DEALS = String.format("create table %s"
                    +" (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text)",
            DatabaseContract.TABLE_NYKA_DEALS,
            DatabaseContract.StoreDealsColumns._ID,
            DatabaseContract.StoreDealsColumns.DEAL_HEADING,
            DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,
            DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,
            DatabaseContract.StoreDealsColumns.IS_FAVOURITE,
            DatabaseContract.StoreDealsColumns.STORE_URL);

    private static final String SQL_CREATE_TABLE_SHOPCLUES_DEALS = String.format("create table %s"
                    +" (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s text)",
            DatabaseContract.TABLE_SHOPCLUES_DEALS,
            DatabaseContract.StoreDealsColumns._ID,
            DatabaseContract.StoreDealsColumns.DEAL_HEADING,
            DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,
            DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,
            DatabaseContract.StoreDealsColumns.IS_FAVOURITE,
            DatabaseContract.StoreDealsColumns.STORE_URL);



    public DbHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_TABLE_FLIPKART_DEALS);
        db.execSQL(SQL_CREATE_TABLE_AMAZON_DEALS);
        db.execSQL(SQL_CREATE_TABLE_JABONG_DEALS);
        db.execSQL(SQL_CREATE_TABLE_MYNTRA_DEALS);
        db.execSQL(SQL_CREATE_TABLE_NYKA_DEALS);
        db.execSQL(SQL_CREATE_TABLE_SHOPCLUES_DEALS);

        fillDatabaseWithData(db);
    }


    /**
     * Adds the initial data set to the database.
     * @param db Database to fill with data since the member variables are not initialized yet.
     *
     * Since this is a sample app, we are populating it with existing sample data. In production version,
     * we need to make webservice API calls on an Intent service in to order to fetch the actual deals related
     * data from the server
     */
    private void fillDatabaseWithData(SQLiteDatabase db) {

        String[] allDeals_Deal_Description= {"desc 1","desc 2","desc 3","desc 4","desc 5","desc 6"};
        String[] allDeals_Cash_Back_Offer= {"offer 1","offer 2","offer 3","offer 4","offer 5","offer 6"};


        int[] storeDeals_Is_Favourite={0,0,0};

        String[] flipkartStoreDeals_Deal_Heading={"Moto G5 Plus","Lenovo Vibe K5","Asus Zenfone 3"};
        String[] flipkartStoreDeals_Deal_Descripton={"Moto G5 Plus - Buy at Rs 16,999 + Upto Rs 16,000 Exchange Off + 0.75% Cashback",
                                                     "Lenovo Vibe K5 Note - Buy at Rs 12,999 + Upto Rs 12,000 Exchange Off + 0.5% Cashback",
                                                     "Asus Zenfone 3 Laser - Buy at Rs 16,999 + Upto Rs 16,000 Exchange Off + 0.75% Cashback",};
        String[] flipkartStoreDeals_Add_Info={"Expires in 3 Days","Expires in 2 Days","Expires in 3 Days"};
        String[] flipkartStoreDeals_Store_Url={"https://www.flipkart.com/moto-g5-plus-lunar-grey-32-gb/p/itmes2zjvwfncxxr?pid=MOBEQHMGMAUXS5BF&affid=rohanpouri&affExtParam1=CHKR18061196/",
                                               "https://www.flipkart.com/lenovo-vibe-k5-note-grey-64-gb/p/itmepcfqfdx9bdxs?pid=MOBEPCFQRJ6KFYZS&srno=b_1_1&otracker=dynamic_omu_%23OnlyonFlipkart_5_Flat%20%E2%82%B9500%20Off_1ada3ab6-f4e9-471f-95ac-21bcbea24617&lid=LSTMOBEPCFQRJ6KFYZSI4DRRB/",
                                               "https://www.flipkart.com/asus-zenfone-3-laser-gold-32-gb/p/itmehctfxvduw4g9?pid=MOBEHCTFYE67N7HP&srno=b_1_1&otracker=foz_omu_%2523OnlyonFlipkart_11_Flat%20%E2%82%B93%2C000%20Off_91ffcfc5-a187-4d99-9db9-08aafe54e7f1&lid=LSTMOBEHCTFYE67N7HPBWEOZI"};

        String[] amazonStoreDeals_Deal_Heading={"Kindle E-reader","Harry Potter","The Alchemist"};
        String[] amazonStoreDeals_Deal_Descripton={"Buy at Rs 5,999 + 5% Cashback","Buy at Rs 699 + 5% Cashback","Buy at Rs 299 + 2% Cashback",};
        String[] amazonStoreDeals_Add_Info={"Expires in 2 Days","Expires in 3 Days","Expires in 3 Days"};
        String[] amazonStoreDeals_Store_Url={"http://www.amazon.in/All-New-Kindle-E-reader-Glare-Free-Touchscreen/dp/B0186FF45G/ref=sr_1_1?ie=UTF8&qid=1492925911&sr=8-1&keywords=amazon+kindle/",
                                             "http://www.amazon.in/Harry-Potter-Goblet-Fire/dp/1408855682/ref=sr_1_1?ie=UTF8&qid=1492956368&sr=8-1&keywords=harry+potter+and+the+goblet+of+fire/",
                                             "http://www.amazon.in/Alchemist-Paulo-Coelho/dp/8172234988/ref=sr_1_1?s=books&ie=UTF8&qid=1492956435&sr=1-1&keywords=alchemist/"};

        String[] myntraStoreDeals_Deal_Heading={"Roadster T-shirt","Nike T-shirt","Moda T-shirt"};
        String[] myntraStoreDeals_Deal_Descripton={"Buy at Rs 499 + 5% Cashback","Buy at Rs 999 + 5% Cashback","Buy at Rs 5,999 + 5% Cashback",};
        String[] myntraStoreDeals_Add_Info={"Expires in 3 Days","Expires in 4 Days","Expires in 4 Days"};
        String[] myntraStoreDeals_Store_Url={"http://www.myntra.com/tshirts/roadster/roadster-men-black--charcoal-grey-slim-fit-colourblocked-round-neck-t-shirt/1673770/buy/",
                "http://www.myntra.com/tshirts/nike/nike-men-orange-colour-blocked-as-m-nk-t-shirt/1721901/buy",
                "http://www.myntra.com/tshirts/moda-rapido/moda-rapido-navy--white-striped-t-shirt/1010692/buy"};

        String[] nykaStoreDeals_Deal_Heading={"M.A.C Lipstick","Estee Foundation","Bobbi Eyeliner"};
        String[] nykaStoreDeals_Deal_Descripton={"Buy at Rs 1,500 + 5% Cashback","Buy at Rs 3,999 + 2% Cashback","Buy at Rs 5,999 + 5% Cashback",};
        String[] nykaStoreDeals_Add_Info={"Expires in 4 Days","Expires in 2 Days","Expires in 4 Days"};
        String[] nykaStoreDeals_Store_Url={"http://www.nykaa.com/m-a-c-retro-matte-lipstick.html?root=catg&ptype=product&bannerparam=banner&id=90470&nav_path=luxe/",
                "http://www.nykaa.com/estee-lauder-double-wear-stay-in-place-foundation-with-spf-10.html?root=catg&ptype=product&bannerparam=banner&id=81268&nav_path=luxe",
                "http://www.nykaa.com/bobbi-brown-long-wear-gel-eyeliner.html?root=catg&ptype=product&bannerparam=banner&id=102260&nav_path=luxe"};

        String[] jabongStoreDeals_Deal_Heading={"Hoopers Sneakers","Vans Sneakers","Nike Sneakers"};
        String[] jabongStoreDeals_Deal_Descripton={"Buy at Rs 1,960 + 3% Cashback","Buy at Rs 2,250 + 2% Cashback","Buy at Rs 2,772 + 5% Cashback",};
        String[] jabongStoreDeals_Add_Info={"Expires in 4 Days","Expires in 3 Days","Expires in 2 Days"};
        String[] jabongStoreDeals_Store_Url={"http://www.jabong.com/hoopers-White-Sneakers-300053608.html?pos=1&cid=HO246SH58TOIINDFAS/",
                "http://www.jabong.com/vans-Authentic-Black-Sneakers-2685015.html?pos=2&cid=VA613SH84TXVINDFAS/",
                "http://www.jabong.com/Nike-Liteforce-Iii-Mid-Olive-Sneakers-2882881.html?pos=3&cid=NI091SH18AQBINDFAS"};

        String[] shopcluesStoreDeals_Deal_Heading={"Acer Aspire","DATAMINI (Windows)","DATAMINI (Android)"};
        String[] shopcluesStoreDeals_Deal_Descripton={"Buy at Rs 23,990 + 3% Cashback","Buy at Rs 11,499 + 3% Cashback","Buy at Rs 9,999 + 3% Cashback",};
        String[] shopcluesStoreDeals_Add_Info={"Expires in 3 Days","Expires in 2 Days","Expires in 3 Days"};
        String[] shopcluesStoreDeals_Store_Url={"http://www.shopclues.com/acer-aspire-r-11-r3-131t-p8rb-11.6-inch-laptop-pentium-n3700-4gb-500gb-windows-10-home-intel-hd-graphics-blue.html/",
                "http://www.shopclues.com/datamini-twg10-2-in-1-touchscreen-quad-core-2gb-32-gb-windows-10-laptop.html/",
                "http://www.shopclues.com/datamini-twg10-2-in-1-touchscreen-quad-core-2gb-32-gb-android-v5.1-lollipop-2.html/"};


        //Creating containers for the data
        ContentValues allDeals_Values = new ContentValues();
        ContentValues flipkartStoreDeals_Values = new ContentValues();
        ContentValues amazonStoreDeals_Values = new ContentValues();
        ContentValues myntraStoreDeals_Values = new ContentValues();
        ContentValues nykaStoreDeals_Values = new ContentValues();
        ContentValues jabongStoreDeals_Values = new ContentValues();
        ContentValues shopcluesStoreDeals_Values = new ContentValues();



        //Inserting values to table
        for(int i=0;i<allDeals_Deal_Description.length;i++){
          //  allDeals_Values.put(DatabaseContract.AllDealsColumns.DEAL_DESCRIPTION,allDeals_Deal_Description[i]);
       //    allDeals_Values.put(DatabaseContract.AllDealsColumns.CASHBACK_OFFER,allDeals_Cash_Back_Offer[i]);
        //    db.insert(DatabaseContract.TABLE_ALL_DEALS,null,allDeals_Values);
        }
        for(int i=0;i<flipkartStoreDeals_Deal_Heading.length;i++){

            flipkartStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_HEADING,flipkartStoreDeals_Deal_Heading[i]);
            flipkartStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,flipkartStoreDeals_Deal_Descripton[i]);
            flipkartStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,flipkartStoreDeals_Add_Info[i]);
            flipkartStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,storeDeals_Is_Favourite[i]);
            flipkartStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.STORE_URL,flipkartStoreDeals_Store_Url[i]);

            amazonStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_HEADING,amazonStoreDeals_Deal_Heading[i]);
            amazonStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,amazonStoreDeals_Deal_Descripton[i]);
            amazonStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,amazonStoreDeals_Add_Info[i]);
            amazonStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,storeDeals_Is_Favourite[i]);
            amazonStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.STORE_URL,amazonStoreDeals_Store_Url[i]);

            jabongStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_HEADING,jabongStoreDeals_Deal_Heading[i]);
            jabongStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,jabongStoreDeals_Deal_Descripton[i]);
            jabongStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,jabongStoreDeals_Add_Info[i]);
            jabongStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,storeDeals_Is_Favourite[i]);
            jabongStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.STORE_URL,jabongStoreDeals_Store_Url[i]);

            myntraStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_HEADING,myntraStoreDeals_Deal_Heading[i]);
            myntraStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,myntraStoreDeals_Deal_Descripton[i]);
            myntraStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,myntraStoreDeals_Add_Info[i]);
            myntraStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,storeDeals_Is_Favourite[i]);
            myntraStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.STORE_URL,myntraStoreDeals_Store_Url[i]);

            nykaStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_HEADING,nykaStoreDeals_Deal_Heading[i]);
            nykaStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,nykaStoreDeals_Deal_Descripton[i]);
            nykaStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,nykaStoreDeals_Add_Info[i]);
            nykaStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,storeDeals_Is_Favourite[i]);
            nykaStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.STORE_URL,nykaStoreDeals_Store_Url[i]);

            shopcluesStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_HEADING,shopcluesStoreDeals_Deal_Heading[i]);
            shopcluesStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.DEAL_DESCRIPTION,shopcluesStoreDeals_Deal_Descripton[i]);
            shopcluesStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.ADDITIONAL_INFO,shopcluesStoreDeals_Add_Info[i]);
            shopcluesStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,storeDeals_Is_Favourite[i]);
            shopcluesStoreDeals_Values.put(DatabaseContract.StoreDealsColumns.STORE_URL,shopcluesStoreDeals_Store_Url[i]);


            db.insert(DatabaseContract.TABLE_FLIPKART_DEALS,null,flipkartStoreDeals_Values);
            db.insert(DatabaseContract.TABLE_AMAZON_DEALS,null,amazonStoreDeals_Values);
            db.insert(DatabaseContract.TABLE_JABONG_DEALS,null,jabongStoreDeals_Values);
            db.insert(DatabaseContract.TABLE_MYNTRA_DEALS,null,myntraStoreDeals_Values);
            db.insert(DatabaseContract.TABLE_NYKA_DEALS,null,nykaStoreDeals_Values);
            db.insert(DatabaseContract.TABLE_SHOPCLUES_DEALS,null,shopcluesStoreDeals_Values);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("drop table if exists " + DatabaseContract.TABLE_AMAZON_DEALS);
        db.execSQL("drop table if exists " + DatabaseContract.TABLE_FLIPKART_DEALS);
        db.execSQL("drop table if exists " + DatabaseContract.TABLE_JABONG_DEALS);
        db.execSQL("drop table if exists " + DatabaseContract.TABLE_MYNTRA_DEALS);
        db.execSQL("drop table if exists " + DatabaseContract.TABLE_NYKA_DEALS);
        db.execSQL("drop table if exists " + DatabaseContract.TABLE_SHOPCLUES_DEALS);

        onCreate(db);

    }
}
