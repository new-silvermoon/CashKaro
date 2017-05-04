package com.silvermoon.data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * This is required to perform database modification operations (viz, insert,update and delete)
 * Database modification operations always needs to be performed on a worker thread.

 */
public class DealsUpdateService extends IntentService {
    private static final String TAG = DealsUpdateService.class.getSimpleName();
    // Intent Actions
    public static final String ACTION_INSERT = TAG + ".INSERT";
    public static final String ACTION_UPDATE = TAG + ".UPDATE";


    public static final String EXTRA_VALUES = TAG + ".ContentValues";
    public static final String EXTRA_ARGS = TAG + ".Args";

    private static final String MODIFY_COMPLETE_BROADCAST = "com.silvermoon.cashkaro.MODIFY_COMPLETE_BROADCAST";


    public DealsUpdateService() {
        super(TAG);
    }


    public static void updateDeal(Context context,Uri uri,ContentValues values, String[] selectionArgs){
       Intent intent = new Intent(context, DealsUpdateService.class);
       intent.setAction(ACTION_UPDATE);
        intent.setData(uri);
        intent.putExtra(EXTRA_VALUES,values);
        intent.putExtra(EXTRA_ARGS,selectionArgs);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INSERT.equals(action)) {
                final String param1 = null;
                final String param2 = null;
                performInsert(param1, param2);
            } else if (ACTION_UPDATE.equals(action)) {

                ContentValues values = intent.getParcelableExtra(EXTRA_VALUES);
                performUpdate(intent.getData(),values,intent.getStringArrayExtra(EXTRA_ARGS));
            }
        }
    }


    //Dummy Method. not used in this shell app
    private void performInsert(String param1, String param2) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void performUpdate(Uri uri, ContentValues values, String []selectionArgs) {
        int count = getContentResolver().update(uri,values,null,selectionArgs);

        //Sends a custom broadcast after deal has been updated
        Intent customBroadcastIntent = new Intent(MODIFY_COMPLETE_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
