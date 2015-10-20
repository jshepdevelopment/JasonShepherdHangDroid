package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method to use when single player button is pressed
    public void singlePlayer(View view) {
        // build an intent which uses content as first parameter and activity as second param.
        Intent intent = new Intent(this, GameActivity.class);
        // start the intent with startActivity()
        startActivity(intent);
    }
}
