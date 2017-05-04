package com.silvermoon.menubaritems;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.silvermoon.cashkaro.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FBLoginActivity extends AppCompatActivity {

    LoginButton fbLoginButton;
    CallbackManager callbackManager;
    private String userName;
    private SharedPreferences profileInfo;
    private SharedPreferences.Editor editor;
    private TextView tvUserName;
    private static final String TAG = FBLoginActivity.class.getSimpleName();
    private AccessTokenTracker fbTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_fblogin);
        callbackManager = CallbackManager.Factory.create();
        fbLoginButton = (LoginButton)findViewById(R.id.loginButton);
        tvUserName = (TextView)findViewById(R.id.username);

        loginWithFB();

        //Keeps track when user logs in or logs out from the app
        fbTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null) {
                    Toast.makeText(FBLoginActivity.this, "User Logged out", Toast.LENGTH_SHORT).show();
                    userName = "Guest";
                    tvUserName.setText(userName);
                    editor = profileInfo.edit();
                    editor.putString("USER_NAME", null);
                    editor.commit();
                    hideAccountItems();
                }
            }
        };

        profileInfo = getSharedPreferences("PROFILE_INFO", Context.MODE_PRIVATE);
        String userNameValue = profileInfo.getString("USER_NAME",null);

        boolean fragmentExists=false;
        // Restore saved instance state of date
        if (savedInstanceState != null) {
            fragmentExists=savedInstanceState.getBoolean("FRAGMENT_EXISTS");
        }

        if(userNameValue!=null){

            tvUserName.setText(userNameValue);
            if(!fragmentExists)
            displayAccountItems();

        }
        else{tvUserName.setText("Guest");}


        
    }

    private void loginWithFB() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserData(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                Toast.makeText(FBLoginActivity.this, "You have cancelled login process.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    /**
     * Uses Facebook's Graph API in order to fetch Logged in user details
     * in JSON format
     */

    public void getUserData(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {


                        try {
                            //parsing json data
                            userName = object.getString("name");
                            tvUserName.setText(userName);
                            editor = profileInfo.edit();
                            editor.putString("USER_NAME", userName);
                            editor.commit();
                            displayAccountItems();
                        } catch (JSONException e) {
                            Log.i(TAG, "Error in fetching User Name: " + e.toString());

                        }

                        Log.i("Facebook Response", object.toString());

                    }
                }
        );

        Bundle bundle = new Bundle();
        bundle.putString("fields","id,name,link");
        request.setParameters(bundle);
        //Executes the request in Async Worker Thread
        request.executeAsync();
    }


    //Displays account items if user is logged in
    private void displayAccountItems() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AccountItemsFragment itemFragment = new AccountItemsFragment();
        ft.add(R.id.accountItems,itemFragment,"FragmentTransaction_1").commit();

    }
    //Hides account items if user is logged in
    private void hideAccountItems(){
        new Handler().post(new Runnable() {
            public void run() {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fm.findFragmentByTag("FragmentTransaction_1")).commitAllowingStateLoss();
            }});




    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(fm.findFragmentByTag("FragmentTransaction_1")!=null){
            outState.putBoolean("FRAGMENT_EXISTS",true);
        }
        else{outState.putBoolean("FRAGMENT_EXISTS",false);}

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
