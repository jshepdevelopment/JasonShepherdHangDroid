package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Jason Shepherd on 11/11/2015.
 */

public class MultiPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
    }

    // method to use when play button is pressed
    public void playGame(View view) {

        // added because I don't want to create duplicate GameActivity classes
        boolean multiPlayerFlag = true; // a flag to send to the game activity

        // creating a new editText object from XML
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        // get the word and cast it to a String
        String wordToGuess = editText.getText().toString();

        // create an explicit intent then send word and multiplayer flag data using putExtra
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("GUESS_WORD", wordToGuess);
        intent.putExtra("MULTIPLAYER_FLAG", multiPlayerFlag);

        // start the activity
        startActivity(intent);

        // log for debugging purposes
        Log.d("JSLOG", "Word entered is: " + wordToGuess);
        Log.d("JSLOG", "GameActivity started as multi-player.");

    }

}
