package com.silvermoon.cashkaro;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.silvermoon.carouselview.*;
import com.silvermoon.menubaritems.FBLoginActivity;
import com.silvermoon.storepage.*;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CarouselView carouselView;
    private int dealImages[] = {R.drawable.deal1,R.drawable.deal2,R.drawable.deal3,R.drawable.deal4,R.drawable.deal5,R.drawable.deal6};
    private int storeHeader[]={R.drawable.flipkart_store,R.drawable.jabong_store,R.drawable.amazon_store,R.drawable.shopclues_store,R.drawable.nykaa_store,R.drawable.myntra_store};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int CAMERA_REQUEST_PERMISSION_CODE = 101;
    private static final int LOCATION_REQUEST_PERMISSION_CODE = 102;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Setting up drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Setting up carousel view
        carouselView = (CarouselView)findViewById(R.id.carouselView);
        carouselView.setPageCount(dealImages.length);
        carouselView.setViewListener(viewListener);

        //Setting up tabbed layout
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainScreenTabAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_login:
                //invokes FBLoginActivity
                startActivity(new Intent(this, FBLoginActivity.class));
                break;
            case R.id.action_search:
                //Dummy action
                Toast.makeText(this, "This is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_alarm:
                //Dummy action
                Toast.makeText(this, "This is clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //CAMERA PERMISSION
            //Checking for permissions and prompting the user in case the permission is not present
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_REQUEST_PERMISSION_CODE);
            }

        } else if (id == R.id.nav_location) {
            //LOCATION PERMISSION
            //Checking for permissions and prompting the user in case the permission is not present

            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED    ) {
               ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_PERMISSION_CODE);
           }

        }  else if (id == R.id.nav_share) {

            //Share Intent
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Hey, Download this useful app from playstore");
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent,"Share this App!!"));

        } else if (id == R.id.nav_send) {
            //Dummy action
            Toast.makeText(this, "This is clicked", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // To set deal Images
    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(final int position) {

            View customView = getLayoutInflater().inflate(R.layout.deal_view_custom, null);
            ImageView dealImageView = (ImageView) customView.findViewById(R.id.dealImageView);
            dealImageView.setImageResource(dealImages[position]);

            carouselView.setIndicatorGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);

            //Invokes store page activity
            dealImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent storePageIntent = new Intent(v.getContext(),StorePageActivity.class);
                    storePageIntent.setData(Constants.STORE_URI[position]);
                    storePageIntent.putExtra("URL",Constants.URL_VALUES[position]);
                    storePageIntent.putExtra("BANNER_IMAGE",storeHeader[position]);
                    storePageIntent.putExtra("STORE_HEADING",Constants.STORE_HEADING[position]);
                    startActivity(storePageIntent);
                }
            });

            return customView;
        }
    };

    /*Checks the permission status for camera and location and displays the appropriate toast*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      switch(requestCode){
          case CAMERA_REQUEST_PERMISSION_CODE:
              if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
              {
                  Toast.makeText(this, "Camera permission has been granted.", Toast.LENGTH_SHORT).show();
              }
              else{
                  Toast.makeText(this, "Camera permission has been denied", Toast.LENGTH_SHORT).show();
              }
              break;
          case LOCATION_REQUEST_PERMISSION_CODE:
              if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

                  Toast.makeText(this, "Location permission has been granted.", Toast.LENGTH_SHORT).show();
              }
              else{
                  Toast.makeText(this, "Location permission has been denied", Toast.LENGTH_SHORT).show();
              }
              break;
      }

        //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
