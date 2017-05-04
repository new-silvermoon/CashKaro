package com.silvermoon.cashkaro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Adapter class for Tabs present in Main Activity
 */

public class MainScreenTabAdapter extends FragmentPagerAdapter{

    String data [] ={"Electronics","Movies","Books"};

    public MainScreenTabAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MainScreenDealsFragment();
            case 1:
                return new MainScreenDealsFragmentTwo();
            case 2:
                return new MainScreenDealsFragmentThree();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return data.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }
}
