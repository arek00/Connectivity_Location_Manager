package com.example.managers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void openLocationActivity(View view)
    {
        Intent locationActivity = new Intent(this,LocationManagement.class);
        startActivity(locationActivity);
        finish();
    }

    public void openConnectionActivity(View view)
    {
        Intent connectionActivity = new Intent(this,ConnectionManagement.class);
        startActivity(connectionActivity);
        finish();
    }

}
