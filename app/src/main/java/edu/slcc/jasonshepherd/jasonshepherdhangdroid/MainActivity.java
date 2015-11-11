package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Jason Shepherd on 10/21/2015.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // use the window manager to make fullscreen menu
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    // method to use when single player button is pressed
    public void singlePlayer(View view) {
        // build an explicit intent which uses content as first parameter and activity as second param.
        // then start the intent with startActivity() and log that the GameActivity intent has started
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        Log.d("JSLOG", "singlePlayer selected. GameActivity started.");

    }

    // method to use when two player button is pressed
    public void multiPlayer(View view) {
        // building another explicit intent and starting multiplayer activity then log for debug
        Intent intent = new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);
        Log.d("JSLOG", "multiPlayer selected. MultiPlayerActivity started.");

    }

    // method to use when high scores button is pressed
    public void seeHighScores(View view) {
        // another explicit intent to start the high score activity then log for debug
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
        Log.d("JSLOG", "High Scores selected. HighScoresActivity started.");

    }
}
