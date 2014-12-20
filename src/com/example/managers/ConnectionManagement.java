package com.example.managers;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * Created by Admin on 2014-12-20.
 */
public class ConnectionManagement extends Activity {

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
    }





}
