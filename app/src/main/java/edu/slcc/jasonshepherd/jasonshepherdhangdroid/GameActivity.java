package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends AppCompatActivity {

    //Declare a log TAG called JSLOG for logging purposes
    private static final String JSLOG = "JSLOG";
    public String currentGameWord;

    // needed to assign textViews, one for each letter of word
    private LinearLayout wordLayout;
    private TextView[] letterViews;

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
        if (id == R.id.action_refresh) {
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
        ///TextView gameWordView = (TextView)findViewById(R.id.gameWordView);

        // sets the layout to use for the word
        wordLayout = (LinearLayout)findViewById(R.id.layoutLetters);

        // creates a random number based on the amount of strings in the gameWords array
        Random r = new Random();
        int randomWordCount = r.nextInt(gameWords.length - 0) + 0;

        // assign the current game word from random word
        currentGameWord = gameWords[randomWordCount];

        // array to store text views for each letter of current word
        letterViews = new TextView[currentGameWord.length()];

        // get rid of the text views in the layout, because new views are needed to replace
        // existing views from current word, useful for new game
        wordLayout.removeAllViews();

        // go through each letter of current word to assign to the text views
        for (int c = 0; c < currentGameWord.length(); c++) {
            letterViews[c] = new TextView(this);

            // set the current text view to the current letter
            letterViews[c].setText("" + currentGameWord.charAt(c));

            // set all of the parameters, which we generally did in layout file
            letterViews[c].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            letterViews[c].setGravity(Gravity.CENTER);
            letterViews[c].setTextColor(Color.GRAY);
            letterViews[c].setTextSize(30);
            letterViews[c].setBackgroundResource(R.drawable.underscore);

            // finally add it to layout
            wordLayout.addView(letterViews[c]);
        }

        // send a Toast to inform player(s) that the new word is ready
        Toast.makeText(this, "A new word is ready.", Toast.LENGTH_SHORT).show();

        // log the current game word
        Log.d(JSLOG, "Game word " + currentGameWord + " set successfully.");

    }

    public void checkLetter(View view) {

        // check which letter the user has pressed  by looking at the letter in the textview
        String letterString = ((TextView)view).getText().toString();
        char aLetter = letterString.charAt(0);

        // track to see if a letter was found in the word
        boolean letterGuessed = false;

        // loop will check if the letter guessed, matches a letter in the word, along with
        // log whether the letter has been found or not
        for(int i = 0; i < currentGameWord.length(); i++) {
            if (currentGameWord.charAt(i) == aLetter) {
                letterGuessed = true;

                Toast.makeText(this, "Letter " + aLetter + " found!", Toast.LENGTH_SHORT).show();
                Log.d(JSLOG, "Player found letter: " + aLetter);
            }
        }

        // log the letter that was selected and show a toast to the user
        Log.d(JSLOG, "Player selected letter: " + aLetter);
        Toast.makeText(this, "You chose letter " + aLetter + ".", Toast.LENGTH_SHORT).show();

    }

}
