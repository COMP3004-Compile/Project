package com.example.jeremy.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer autoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // whatever you have here
    }

    @Override
    public void onResume() {
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        updatePage();
                    }
                });
            }
        }, 0, 20000); // updates each 20 secs
    }

    private void updatePage(){
        // update any activity here
        // Toast is used for testing purposes only
        Toast.makeText(MainActivity.this,"testing every 20 secs",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        autoUpdate.cancel(); // pauses the updatePage task
        super.onPause();
    }
}