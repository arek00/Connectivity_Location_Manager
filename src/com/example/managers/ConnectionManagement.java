package com.example.managers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by Admin on 2014-12-20.
 */
public class ConnectionManagement extends Activity {

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private Connection connection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }



    public void setWifi(View view)
    {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }

    public void setEthernet(View view)
    {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
    }

    public void setWimax(View view)
    {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX);
    }

    public void setMMS(View view)
    {
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE_MMS);
    }

    public void connect(View view)
    {
        EditText addressField = (EditText)findViewById(R.id.urlAddressField);
        String urlAddress = addressField.getText().toString();
        setInProgressStatus();
        
        this.connection = new Connection(urlAddress);
        setConnectionStatus();
    }


    public void setConnectionStatus()
    {
        TextView textView = (TextView)findViewById(R.id.connectionStatus);
        String text;

        if(connection.getConnectionStatus().equals("Completed"))
        {
            text = connection.getDownloadReadableInformations();
        }
        else
        {
            text = connection.getConnectionStatus();
        }

        textView.setText(text);
    }

    public void setInProgressStatus()
    {
        TextView textView = (TextView)findViewById(R.id.connectionStatus);
        String text = "Pobieranie w toku";
        textView.setText(text);
    }


}
