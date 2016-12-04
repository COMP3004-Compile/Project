package com.example.androidlocationservice;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class AndroidLocationService extends Activity {
    public final static String LONGITUDE = "Longitude";
    public final static String LATITIDE = "Latitidue";

    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };

        int increment = 4;
        MyLocation myLocation = new MyLocation();

        // private ProgressDialog dialog;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_android_location_service);

            if (!canAccessLocation()) {
                requestPermissions(INITIAL_PERMS, 1337);
            }

            myLocation.getLocation(getApplicationContext(), locationResult);

            boolean r = myLocation.getLocation(getApplicationContext(),
                    locationResult);

            // startActivity(new Intent(AndroidLocationService.this,
                    // Nearbyhotelfinder.class));
            //       SomeClass));
            finish();
        }

        public MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {

            @Override
            public void gotLocation(Location location) {
                // TODO Auto-generated method stub
                double Longitude = location.getLongitude();
                double Latitude = location.getLatitude();

                System.out.println("The longitude is [" + Longitude + "]");
                System.out.println("The latitude is [" + Latitude + "]");

                //Intent intent = new Intent(this, DisplayMessageActivity.class);
                //intent.putExtra(LONGITUDE, Longitude);
                //intent.putExtra(LATITIDE, Latitude);
                //startActivity(intent);

                // In the activity that was started pass get the coords
                //Intent intent = getIntent();
                //String longigute = intent.getStringExtra(AndroidLocationService.LONGITUDE);
                //String latitude = intent.getStringExtra(AndroidLocationService.LATITIDE);
            }
        };

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }
        // handler for the background updating

    }

