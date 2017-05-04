package com.silvermoon.menubaritems;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silvermoon.cashkaro.R;

/**
 * Fragment to display sample account items after user has successfully logged in
 */
public class AccountItemsFragment extends Fragment {


    public AccountItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_items, container, false);
    }

}
