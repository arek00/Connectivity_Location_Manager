package com.example.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.location.*;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

/**
 * Created by Admin on 2014-12-20.
 */
public class LocationManagement extends Activity implements LocationListener{

    LocationManager locationManager;
    Criteria criteria;
    String locationProvider;
    Location location;
    TextView longitudeView;
    TextView latitudeView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        longitudeView = (TextView)findViewById(R.id.longitude_value);
        latitudeView = (TextView)findViewById(R.id.latitude_value);

        this.locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        if(!this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Intent backToMyActivity = new Intent(this,MyActivity.class);
            startActivity(backToMyActivity);
            finish();
            Toast toast = Toast.makeText(this,"Turn on GPS",Toast.LENGTH_LONG);
            toast.show();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

        this.criteria = new Criteria();
        this.criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        this.criteria.setCostAllowed(false);
        locationProvider = locationManager.getBestProvider(this.criteria,false);
        location = locationManager.getLastKnownLocation(locationProvider);


        if (location != null) {
            onLocationChanged(location);
        } else {
            latitudeView.setText("Location not available");
            longitudeView.setText("Location not available");
        }
    }

    public void setCoarse(View view)
    {
        this.criteria.setAccuracy(Criteria.ACCURACY_COARSE);
    }

    public void setLow(View view)
    {
        this.criteria.setAccuracy(Criteria.ACCURACY_LOW);
    }
    public void setMedium(View view)
    {
        this.criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
    }
    public void setHigh(View view)
    {
        this.criteria.setAccuracy(Criteria.ACCURACY_HIGH);
    }
    public void setFine(View view)
    {
        this.criteria.setAccuracy(Criteria.ACCURACY_FINE);
    }

    @Override
    public void onLocationChanged(Location location) {

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        longitudeView.setText(Double.toString(longitude));
        latitudeView.setText(Double.toString(latitude));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
