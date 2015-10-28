package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    // imageview used to update hangdroid graphics
    ImageView hangDroidView;

    // track to see if a letter was found in the word, or if it was missed
    int letterGuessed;
    int letterMissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Set the hangroid view here, so that when a new word is created, there will
        // not be a reference to null object.
        hangDroidView = (ImageView)findViewById(R.id.hangDroidView);

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

        // action to call when refresh selected
        if (id == R.id.action_refresh) {
            setGameWord();
            return true;
        }

        // action to call when display about selected
        if (id == R.id.action_about) {
            displayAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // method to set game word at random
    private void setGameWord() {
        setContentView(R.layout.activity_game);
        hangDroidView = (ImageView)findViewById(R.id.hangDroidView);

        // image views used for hangman, will need to set after content view inflated
        hangDroidView.setImageResource(R.drawable.hangdroid_0);

        // assign an array of strings to gameWords and set layout to use for the word
        String[] gameWords = getResources().getStringArray(R.array.game_words);
        wordLayout = (LinearLayout)findViewById(R.id.layoutLetters);

        // creates a random number based on the amount of strings in the gameWords array and then
        // assigns the current game word from random word
        Random r = new Random();
        int randomWordCount = r.nextInt(gameWords.length - 0) + 0;
        currentGameWord = gameWords[randomWordCount];

        // array to store text views for each letter of current word and the underscores
        letterViews = new TextView[currentGameWord.length()];

        // get rid of the text views in the layout, because new views are needed to replace
        // existing views from current word if a new game. ie new word is needed
        wordLayout.removeAllViews();

        // go through each letter of current word to assign to the text views
        for (int c = 0; c < currentGameWord.length(); c++) {
            letterViews[c] = new TextView(this);

            // set the current text view to the current letter
            letterViews[c].setText("" + currentGameWord.charAt(c));

            // set all of the parameters, which we generally set in layout file and hide the letters
            letterViews[c].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            letterViews[c].setGravity(Gravity.CENTER);
            letterViews[c].setTextColor(Color.WHITE);
            letterViews[c].setTextSize(30);
            letterViews[c].setBackgroundResource(R.drawable.underscore);

            // finally add it to layout
            wordLayout.addView(letterViews[c]);
        }

        // reset letter guessed and letter missed amount
        letterGuessed = 0;
        letterMissed = 0;

        // send a Toast to inform player(s) that the new word is ready
        Toast.makeText(this, "A new word is ready.", Toast.LENGTH_SHORT).show();

        // log the current game word
        Log.d(JSLOG, "Game word " + currentGameWord + " set successfully.");

       }

    public void checkLetter(View view) {

        // first hide the button that was just pressed then check which letter the user has pressed
        //  by looking at the letter in the textview. Finally store as char and log
        ((TextView)view).setVisibility(View.GONE);
        String letterString = ((TextView)view).getText().toString();
        char aLetter = letterString.charAt(0);
        Log.d(JSLOG, "Player selected letter: " + aLetter);

        // used to track whether a letter has been guessed correctly or missed
        boolean letterGuessedFlag = false;
        boolean letterMissedFlag = false;

        // loop will check if the letter guessed, matches a letter in the word, along with
        // logging whether the letter has been found or not
        for(int i = 0; i < currentGameWord.length(); i++) {

            // Has guessed correctly
            if (currentGameWord.charAt(i) == aLetter) {
                letterGuessedFlag = true;
                // it is necessary to increment counter here because some words have the same
                // letter more than once
                letterGuessed++;

                // show the corresponding letter when the letter matches the guess
                letterViews[i].setTextColor(Color.BLACK);
                Toast.makeText(this, "Letter " + aLetter + " found!", Toast.LENGTH_SHORT).show();
                Log.d(JSLOG, "Player found letter: " + aLetter);
            }
        }

        //check for letters guessed or missed, reset flags and increment counters
        if (letterGuessedFlag) {
            letterGuessedFlag = false;
            Log.d(JSLOG, "Player has correctly guessed " + letterGuessed + " letters.");
        } else letterMissedFlag = true;

        if (letterMissedFlag) {
            letterMissedFlag = false;
            letterMissed++;
            Toast.makeText(this, "There is no " + aLetter + "! Try again!", Toast.LENGTH_SHORT).show();
            Log.d(JSLOG, "Player has missed " + letterMissed + " times.");
        }

        // display image based on missed guesses
        if (letterMissed == 1)hangDroidView.setImageResource(R.drawable.hangdroid_1);
        if (letterMissed == 2)hangDroidView.setImageResource(R.drawable.hangdroid_2);
        if (letterMissed == 3)hangDroidView.setImageResource(R.drawable.hangdroid_3);
        if (letterMissed == 4)hangDroidView.setImageResource(R.drawable.hangdroid_4);
        if (letterMissed == 5)hangDroidView.setImageResource(R.drawable.hangdroid_5);

        // check for winner
        if (letterGuessed == currentGameWord.length()) {
            Toast.makeText(this, "You are Winner! Ha ha ha.", Toast.LENGTH_SHORT).show();
            Log.d(JSLOG, "We have a winner! Word " + currentGameWord + " was solved!");
            displayWin();
        }

        // check for loser
        if (letterMissed == 5) {
            Toast.makeText(this, "You are Loser! Ha ha ha.", Toast.LENGTH_SHORT).show();
            Log.d(JSLOG, "We have a loser! Word " + currentGameWord + " was not solved!");

            // show the word to the loser
            for (int c = 0; c < currentGameWord.length(); c++) {
                letterViews[c].setTextColor(Color.RED);
            }
            displayLoss();
        }
    }


    private void displayWin() {
        // Display Alert Dialog
        AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
        winBuild.setTitle("Congrats!");
        winBuild.setMessage("You win!\n\nThe answer was:\n\n"+currentGameWord);
        winBuild.setPositiveButton("Play Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameActivity.this.setGameWord();
                    }});

        winBuild.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameActivity.this.finish();
                    }});

        winBuild.show();
    }

    private void displayLoss() {
        // Display Alert Dialog
        AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
        winBuild.setTitle("Shucks");
        winBuild.setMessage("You lose!\n\nThe answer was:\n\n"+currentGameWord);
        winBuild.setPositiveButton("Play Again",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameActivity.this.setGameWord();
                    }});

        winBuild.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameActivity.this.finish();
                    }});

        winBuild.show();
    }

    private void displayAbout() {
        // Display Alert Dialog
        AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
        winBuild.setTitle("JS HangDroid");
        winBuild.setMessage("A hangman game.\nDeveloped for Buhler's CSIS 2630.\nVersion 1.0");
        winBuild.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        winBuild.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameActivity.this.finish();
                    }});

        winBuild.show();
    }
}
