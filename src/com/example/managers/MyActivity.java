package com.example.managers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void openLocationActivity()
    {
        Intent locationActivity = new Intent(this,LocationManagement.class);
        startActivity(locationActivity);
    }

    public void openConnectionActivity()
    {
        Intent connectionActivity = new Intent(this,ConnectionManagement.class);
        startActivity(connectionActivity);
    }

}
