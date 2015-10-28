package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    //Declare a log TAG called JSLOG for logging purposes
    private static final String JSLOG = "JSLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // use the window manager to make fullscreen menu
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    // Method to use when single player button is pressed
    public void singlePlayer(View view) {

        // build an explicit intent which uses content as first parameter and activity as second param.
        // then start the intent with startActivity() and log that the GameActivity intent has started
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        Log.d(JSLOG, "singlePlayer selected. GameActivity intent started.");

    }
}
