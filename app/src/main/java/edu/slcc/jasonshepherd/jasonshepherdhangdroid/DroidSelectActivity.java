package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


/**
 * Created by Jason Shepherd on 10/30/2015.
 */

public class DroidSelectActivity extends Activity {

    // variable to hold id of selected droid
    int droidSelectId;
    private static final String JSLOG = "JSLOG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_droidselect);

    }

    // method called when droid is selected. will set droidSelectId then build an intent to
    // move to the game activity
    public void droidSelectInit(View view) {

        // set droidSelectId
        switch (view.getId()) {
            case R.id.droid1:
                droidSelectId = 1;
                break;
            case R.id.droid2:
                droidSelectId = 2;
                break;

        }

        Log.d(JSLOG, "Droid selection complete. Droid " + droidSelectId + " selected.");



        // Start game activity and send the droidSelectID using putExtra() method
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("droidSelectId", droidSelectId);
        startActivity(intent);

        // Use finish() to prevent return to splash activity
        finish();

        Log.d(JSLOG, "GameActivity intent started.");
    }

}
