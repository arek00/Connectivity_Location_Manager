package com.example.managers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 2014-12-20.
 */
public class ConnectionManagement extends Activity {

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private NetworkInfo activeNetworkInfo;
    private Connection connection;

    private String LOG_TAG = "ConnectivityManager info: ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        Log.i(LOG_TAG, "Starting program");

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        setActiveNetworkInfo(activeNetworkInfo);

        setConnectivityType(networkInfo);

    }


    public void setActiveNetworkInfo(NetworkInfo info) {
        String networkName = info.getTypeName();
        String networkState = info.getState().toString();

        TextView networkNameView = (TextView) findViewById(R.id.current_connection_name);
        TextView networkStateView = (TextView) findViewById(R.id.current_connection_state);

        networkNameView.setText(networkName);
        networkStateView.setText(networkState);

    }

    public void setWifi(View view) {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        setConnectivityType(networkInfo);
    }

    public void setEthernet(View view) {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        setConnectivityType(networkInfo);
    }

    public void setWimax(View view) {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
        setConnectivityType(networkInfo);
    }

    public void setMMS(View view) {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE_MMS);
        setConnectivityType(networkInfo);

    }

    public void setMobile(View view) {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        setConnectivityType(networkInfo);

    }

    private void setConnectivityType(NetworkInfo networkInfo) {
        String networkTypeName;
        String networkState;
        boolean connected = false;


        if (networkInfo != null) {
            networkTypeName = networkInfo.getTypeName();
            networkState = networkInfo.getState().toString();
            connected = networkInfo.isConnected();

        } else {
            networkTypeName = "Can't be obtain";
            networkState = "Unknown";
        }


        TextView connectivityTypeText = (TextView) findViewById(R.id.connection_type_name);
        connectivityTypeText.setText(networkTypeName);

        TextView connectivityStateText = (TextView) findViewById(R.id.connection_state_description);
        connectivityStateText.setText(networkState);


        Toast toast = Toast.makeText(getApplicationContext(), networkTypeName + " " + networkState + " " + connected, Toast.LENGTH_SHORT);
        toast.show();
    }

}