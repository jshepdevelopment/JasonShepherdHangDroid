package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Method designates a word at random to be used as the current game word
        setGameWord();
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            setGameWord();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // method to set game word at random
    private void setGameWord() {

        // assign an array of strings to gameWords
        String[] gameWords = getResources().getStringArray(R.array.game_words);
        // assigns a TextView using the view specified in layout file
        TextView gameWordView = (TextView)findViewById(R.id.gameWordView);
        // creates a random number based on the amount of strings in the gameWords array
        Random r = new Random();
        int randomWordCount = r.nextInt(gameWords.length - 0) + 0;
        //assigns a string to the text view
        gameWordView.setText(gameWords[randomWordCount]);

    }
}
