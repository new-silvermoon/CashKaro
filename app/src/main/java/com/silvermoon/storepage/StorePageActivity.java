package com.silvermoon.storepage;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.silvermoon.cashkaro.R;
import com.silvermoon.data.DatabaseContract;
import com.silvermoon.data.DealsUpdateService;
import com.silvermoon.data.StoreDeals;

import org.w3c.dom.Text;


/*
* Store page Activity.
* Includes Loader callbacks in order to make the database querying operation lifecycle and orientation aware.
* */
public class StorePageActivity extends AppCompatActivity implements
    StorePageAdapter.OnButtonClickListener,
    View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor>{

    private StorePageAdapter mAdapter;
    private Cursor cursor;
    private String queryUri;
    private String urlValue;
    private int bannerImage;
    private RelativeLayout storeBannerLayout;
    Uri storeUri;
    private static final String MODIFY_COMPLETE_BROADCAST = "com.silvermoon.cashkaro.MODIFY_COMPLETE_BROADCAST";
    private ModifyBroadcastReceiver modifyBroadcastReceiver;
    private TextView storePageHeading;
    private LinearLayout storeParentlayout;
    private ImageView storeHeaderImage;
    private FirebaseAnalytics mFirebaseAnalytics;

    private static final int NOTIFICATION_ID = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        storeBannerLayout = (RelativeLayout)findViewById(R.id.storeBannerLayout);
        storePageHeading=(TextView)findViewById(R.id.storeHeading);
        storeParentlayout=(LinearLayout)findViewById(R.id.storeParentLayout);
        storeHeaderImage =(ImageView)findViewById(R.id.headerImage);

        modifyBroadcastReceiver = new ModifyBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(modifyBroadcastReceiver,new IntentFilter(MODIFY_COMPLETE_BROADCAST));

        //Obtaining the Firebase Analytics instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Intent intent = getIntent();
        storeUri = intent.getData();
        queryUri = storeUri.toString();
        urlValue = intent.getStringExtra("URL");
        bannerImage = intent.getIntExtra("BANNER_IMAGE",0);
        storePageHeading.setText(intent.getStringExtra("STORE_HEADING"));

       storeHeaderImage.setImageResource(bannerImage);
        // storeBannerLayout.setBackgroundResource(bannerImage);

        getSupportLoaderManager().initLoader(0,null,this);

        mAdapter = new StorePageAdapter(cursor);
        mAdapter.setOnItemClickListener(this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.store_page_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    //Opens Website homepage
    public void openStoreWebView(View view) {

        final View myView = view;
        //Getting network information
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()) {

            //Displaying Alert box
            AlertDialog.Builder webViewAlertBuilder = new AlertDialog.Builder(StorePageActivity.this);
            webViewAlertBuilder.setTitle("Important steps to ensure your Cashback tracks!");
            webViewAlertBuilder.setMessage("Shopping website rules");
            webViewAlertBuilder.setPositiveButton("I UNDERSTAND,VISIT RETAILER", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent webViewIntent = new Intent(myView.getContext(), WebViewActivity.class);
                    webViewIntent.putExtra("URL", urlValue);
                    startActivity(webViewIntent);
                }
            });
            webViewAlertBuilder.show();


        }
        else{
            Toast.makeText(this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{DatabaseContract.TABLE_FLIPKART_DEALS};
        String sortOrder = DatabaseContract.STORE_DEALS_SORT_ORDER;

        return new CursorLoader(this, Uri.parse(queryUri),null,null,null,sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursor = data;
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onfavClick(boolean fav,long id,StoreDeals deals) {

        Uri updateURI = ContentUris.withAppendedId(storeUri,id);
        ContentValues values = new ContentValues();
        String [] selectionArgs = new String[]{String.valueOf(id)};

        if(fav){
            values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,"0");
            DealsUpdateService.updateDeal(this,updateURI,values,selectionArgs);



        }
        else{
            values.put(DatabaseContract.StoreDealsColumns.IS_FAVOURITE,"1");
            DealsUpdateService.updateDeal(this,updateURI,values,selectionArgs);

            /*Generating logs for Firebase
            This is help in gathering information about the type of items that are popular amongst the users
             */
            Bundle b= new Bundle();
            b.putString(FirebaseAnalytics.Param.ITEM_ID,Long.toString(id));
            b.putString(FirebaseAnalytics.Param.ITEM_NAME,deals.dealHeading);
            b.putString(FirebaseAnalytics.Param.ITEM_CATEGORY,"CATEGORY");
            b.putLong(FirebaseAnalytics.Param.QUANTITY,1);
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST,b);

        }

    }


    //Share intent
    @Override
    public void onShareClick(StoreDeals deals) {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey I found this deal through CashKaro.\n Details :"+deals.dealHeading
                +"\n"+deals.dealDesciption+"\n"+deals.additionalInfo);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent,"Share this Deal!!"));

    }

    //Displays notification for every click on Deal button
    @Override
    public void onGetCodeClick(final StoreDeals deals) {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification note = new NotificationCompat.Builder(this)
                .setContentTitle("Congratulations! You have clicked on "+deals.dealHeading)
                .setContentText(deals.dealDesciption)
                .setSmallIcon(R.drawable.ic_deal_notify)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .build();


        manager.notify(NOTIFICATION_ID,note);

        //Generating log for firebase
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"Get_Code Button");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,"Get Code");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"button");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);

        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo !=null && networkInfo.isConnected()) {

            //Displaying Alert box
            AlertDialog.Builder webViewAlertBuilder = new AlertDialog.Builder(StorePageActivity.this);
            webViewAlertBuilder.setTitle("Important steps to ensure your Cashback tracks!");
            webViewAlertBuilder.setMessage("Shopping website rules");
            webViewAlertBuilder.setPositiveButton("I UNDERSTAND,VISIT RETAILER", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent webViewIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                    webViewIntent.putExtra("URL", deals.storeUrl);
                    startActivity(webViewIntent);
                }
            });
            webViewAlertBuilder.show();


        }
        else{
            Toast.makeText(this, "Please check your Network Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(modifyBroadcastReceiver);
        super.onDestroy();
    }


    /*Broadcast receiver. Receives broadacast from Intent service regarding the
    completion of database operation and restarts the loader in order to refresh the
    updated data in recycler view
    */
    public class ModifyBroadcastReceiver extends BroadcastReceiver{
        private String intentAction;

        @Override
        public void onReceive(Context context, Intent intent) {

            intentAction = intent.getAction();
            if(intentAction==MODIFY_COMPLETE_BROADCAST){
                getSupportLoaderManager().restartLoader(0,null,StorePageActivity.this);
                Snackbar.make(storeParentlayout,"Deal favourite status has been updated",Snackbar.LENGTH_SHORT).show();
            }
        }
    }


}
