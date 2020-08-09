package com.usamaqadeer.freshveg.activities.User.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usamaqadeer.freshveg.R;

public class UserOrdersFragment extends Fragment {

    public UserOrdersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_orders, container, false);



        return view;
    }
}