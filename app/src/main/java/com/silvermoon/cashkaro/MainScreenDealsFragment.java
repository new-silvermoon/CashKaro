package com.silvermoon.cashkaro;



import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.silvermoon.storepage.WebViewActivity;


/**
 * Fragment class for Electronics Tab
 */
public class MainScreenDealsFragment extends Fragment
         {
             public static final String NOTIFICATION_SERVICE = "notification";
             private static final int NOTIFICATION_ID = 8;
             private FirebaseAnalytics mFirebaseAnalytics;
             private ImageButton shareButton,shareButtonTwo,favButton,favButtonTwo;
             private Button getOfferButton,getOfferButtonTwo;
             private TextView dealHeading,dealDesc,additionalInfo,dealHeading2,dealDesc2,additionalInfo2;


             public MainScreenDealsFragment() {
        // Required empty public constructor
    }

             @Override
             public void onCreate(@Nullable Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);

                 //Obtaining the Firebase Analytics instance
                 mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
             }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen_deals, container, false);
        shareButton = (ImageButton)view.findViewById(R.id.shareButton);
        shareButtonTwo = (ImageButton)view.findViewById(R.id.shareButton2);
        favButton=(ImageButton)view.findViewById(R.id.favButton);
        favButtonTwo=(ImageButton)view.findViewById(R.id.favButton2);
        getOfferButton = (Button)view.findViewById(R.id.getCodeButton);
        getOfferButtonTwo=(Button)view.findViewById(R.id.getCodeButton2);
        dealHeading=(TextView)view.findViewById(R.id.couponTitle);
        dealDesc=(TextView)view.findViewById(R.id.couponDetails);
        additionalInfo=(TextView)view.findViewById(R.id.additionalInfo);
        dealHeading2=(TextView)view.findViewById(R.id.couponTitle2);
        dealDesc2=(TextView)view.findViewById(R.id.couponDetails2);
        additionalInfo2=(TextView)view.findViewById(R.id.additionalInfo2);


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey I found this deal through CashKaro.\n Details :"+dealHeading.getText()
                        +"\n"+dealDesc.getText()+"\n"+additionalInfo.getText());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Share this Deal!!"));
            }
        });
        shareButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey I found this deal through CashKaro.\n Details :"+dealHeading2.getText()
                        +"\n"+dealDesc2.getText()+"\n"+additionalInfo2.getText());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent,"Share this Deal!!"));

            }
        });

        getOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

                Notification note = new NotificationCompat.Builder(getContext())
                        .setContentTitle("Congratulations! You have clicked on "+dealHeading.getText())
                        .setContentText(dealDesc.getText())
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

                ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo !=null && networkInfo.isConnected()) {

                    //Displaying Alert box
                    AlertDialog.Builder webViewAlertBuilder = new AlertDialog.Builder(getContext());
                    webViewAlertBuilder.setTitle("Important steps to ensure your Cashback tracks!");
                    webViewAlertBuilder.setMessage("Shopping website rules");
                    webViewAlertBuilder.setPositiveButton("I UNDERSTAND,VISIT RETAILER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent webViewIntent = new Intent(getContext(), WebViewActivity.class);
                            webViewIntent.putExtra("URL", "https://www.flipkart.com/microsoft-xbox-360e-500-gb-fable-anniversary-plants-vs-zombies-garden-warfare/p/itmecjytrhqnwuep?pid=GMCECJYTPEQFENXB&srno=s_1_3&otracker=search&lid=LSTGMCECJYTPEQFENXB4ZOAOI&qH=9edab8900ac1f80e");
                            startActivity(webViewIntent);
                        }
                    });
                    webViewAlertBuilder.show();


                }
                else{
                    Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getOfferButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

                Notification note = new NotificationCompat.Builder(getContext())
                        .setContentTitle("Congratulations! You have clicked on "+dealHeading2.getText())
                        .setContentText(dealDesc2.getText())
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

                ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo !=null && networkInfo.isConnected()) {

                    //Displaying Alert box
                    AlertDialog.Builder webViewAlertBuilder = new AlertDialog.Builder(getContext());
                    webViewAlertBuilder.setTitle("Important steps to ensure your Cashback tracks!");
                    webViewAlertBuilder.setMessage("Shopping website rules");
                    webViewAlertBuilder.setPositiveButton("I UNDERSTAND,VISIT RETAILER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent webViewIntent = new Intent(getContext(), WebViewActivity.class);
                            webViewIntent.putExtra("URL", "https://www.flipkart.com/sony-ps3-blu-ray-family-bundle-gaming-accessory-kit/p/itmeh2jw3p4t96fk?pid=ACCEH2JWZFF6NR4A&srno=s_1_1&otracker=search&lid=LSTACCEH2JWZFF6NR4AYG5OIE&qH=5260b615ca0c10c0");
                            startActivity(webViewIntent);
                        }
                    });
                    webViewAlertBuilder.show();


                }
                else{
                    Toast.makeText(getContext(), "Please check your Network Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }



    }



