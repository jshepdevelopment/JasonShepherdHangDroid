package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use getWindow() to set the layout parameters to fullscreen
        // This will effectively hide the system's status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // getSupportActionBar() is used to get a reference to the activity's action bar
        // adding the .hide() to hide the activities action bar
        //getSupportActionBar().hide();

        // important to set content view after the above methods, to avoid crash
        setContentView(R.layout.activity_splash);

        // New thread
        Runnable wait3seconds = new Runnable() {
            public void run() {
                nextActivity();
            }
        };

        // Creates a handler to wait and display splash screen for 3 seconds
        Handler handler = new Handler();
        handler.postDelayed(wait3seconds, 3000);
    }

    // Method to create an intent and use startActivity() to pass created intent
    // will start the Activity which is second parameter provided to new Intent
    public void nextActivity() {

        // Start next activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // Use finish() to prevent return to splash activity
        finish();
    }
}
