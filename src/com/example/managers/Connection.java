package com.example.managers;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 2014-12-20.
 */
public class Connection extends AsyncTask {

    private long startDownloadTime;
    private long finishDownloadTime;
    private int size;
    private String status;

    public Connection(String address)
    {
        status = doInBackground(address);
    }


    @Override
    protected String doInBackground(Object... objects) {

        try {
            URL url = new URL((String) objects[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(false);

            InputStream inputStream = connection.getInputStream();

            startDownloadTime = System.nanoTime();
            connection.connect();
            finishDownloadTime = System.nanoTime();
            size = Connection.calculateSize(inputStream);

        }
        catch (MalformedURLException e)
        {
            return "MalformedUrl";

        } catch (IOException e) {
            return "IOException";
        }

        return "Completed";
    }

    public static int calculateSize(InputStream inputStream) throws IOException {
        int size = 0;
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
