package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;


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

        // Create fragment manager. Allows interaction with fragment in an activity.
        FragmentManager fragmentManager = getFragmentManager();

        // Create fragment transaction. Allows us to call methods to manage fragments... show, etc.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Set to change fragments on orientation - first get configuration information
        Configuration configuration = getResources().getConfiguration();

        // Check configuration orientation, and hide panel if in portrait orientation
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
        }

        // Used to invoke Fragment changes on Activity
        fragmentTransaction.commit();

    }

    // Hide the side panel if it is visible
    private void hideSidePanel() {
        View sidePane = findViewById(R.id.side_panel);
        if (sidePane.getVisibility() == View.VISIBLE) {
            sidePane.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(edu.slcc.jasonshepherd.jasonshepherdhangdroid.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
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