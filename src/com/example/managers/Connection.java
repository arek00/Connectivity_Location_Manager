package com.example.managers;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 2014-12-20.
 */
public class Connection {

    private long startDownloadTime;
    private long finishDownloadTime;
    private int size;
    private String status;
    private DownloadManager downloadManager;
    private static Connection instance = new Connection();

    public Connection()
    {

    }

    public static Connection getInstance()
    {
        return instance;
    }

    public void download(String address, Context context)
    {
        downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);

    }


    public int calculateSize(InputStream inputStream) throws IOException {
        int size = 0;

        Log.e("Read data:"," ");
        while( inputStream.read() >= 0 )
        {
            ++size;
        }

        return size;
    }


    public long getDownloadTime()
    {
        return finishDownloadTime - startDownloadTime;
    }

    public double getDownloadTimeInSeconds()
    {
        return (finishDownloadTime - startDownloadTime)/1000000;
    }

    public int getDownloadSizeInBytes()
    {
        return size;
    }

    public double getConnectionSpeed(char unit)
    {
        int scalar = 1;
        unit = Character.toLowerCase(unit);

        switch(unit)
        {
            case 'k': scalar = (int)Math.pow(2,10); break;
            case 'm': scalar = (int)Math.pow(2,20); break;
            case 'g': scalar = (int)Math.pow(2,30); break;
            default: scalar = 1; break;
        }

        return scalar * size / getDownloadTimeInSeconds();
    }

    public double getConnectionSpeed()
    {
        //zwraca prędkość w B/s
        return size/getDownloadTimeInSeconds();
    }

    public String getDownloadReadableInformations()
    {
        String info = String.format("Pobrano %d B danych\n" +
                                    "Średnia prędkość pobierania %f kB/s",getDownloadSizeInBytes(), getConnectionSpeed('k'));

        return info;
    }

    public String getConnectionStatus()
    {
        return this.status;
    }
}
