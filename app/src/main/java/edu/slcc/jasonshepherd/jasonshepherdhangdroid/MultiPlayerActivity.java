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

    public void playGame(View view) {

        boolean multiPlayerFlag = true; // a flag to send to the game activity

        // create new editText object from XML
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        // get word and cast word to a String
        String wordToGuess = editText.getText().toString();
        // create an explicit intent
        Intent intent = new Intent(this, GameActivity.class);
        // send the word with intent
        intent.putExtra("GUESS_WORD", wordToGuess);
        // send a flag to show that this word should be user generated, instead of machine generated
        intent.putExtra("MULTIPLAYER_FLAG", multiPlayerFlag);
        // start the activity
        startActivity(intent);

        // log for debugging purposes
        Log.d("JSLOG", "Word entered is: " + wordToGuess);
        Log.d("JSLOG", "GameActivity started as multi-player.");


    }

}
