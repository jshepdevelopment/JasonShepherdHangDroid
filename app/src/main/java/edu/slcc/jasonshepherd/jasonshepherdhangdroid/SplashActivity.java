package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Jason Shepherd on 10/21/2015.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ese getWindow() to set the layout parameters to fullscreen to hide system status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // important to set content view after the above methods, to avoid crash
        setContentView(R.layout.activity_splash);

        // start a new thread
        Runnable wait3seconds = new Runnable() {
            public void run() {
                nextActivity();
            }
        };

        // creates a handler to wait and display splash screen for 3 seconds
        Handler handler = new Handler();
        handler.postDelayed(wait3seconds, 3000);
    }

    // method to create an intent and use startActivity() to pass created intent
    public void nextActivity() {

        // build an explicit intent to start the next activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        // use finish() to prevent return to splash activity, instead of overriding back button
        finish();

        // log for debugging purposes
        Log.d("JSLOG", "Splashscreen complete. nextActivity() used to start MainActivity intent");
    }
}
