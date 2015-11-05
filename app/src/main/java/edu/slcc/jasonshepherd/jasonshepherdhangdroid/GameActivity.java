package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class GameActivity extends AppCompatActivity {

    //Declare a log TAG called JSLOG for logging purposes
    private static final String JSLOG = "JSLOG";
    public String currentGameWord;

    // needed to assign textViews, one for each letter of word
    private TextView[] letterViews;

    // imageview used to update hangdroid graphics
    ImageView hangDroidView;

    // track to see if a letter was found in the word, or if it was missed and points
    int letterGuessed;
    int letterMissed;
    int points = 0;
    boolean winner = false; // track whether a winner or loser


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
        // handle the action bar clicks
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
        // set up views
        setContentView(R.layout.activity_game);
        hangDroidView = (ImageView)findViewById(R.id.hangDroidView);

        // assign an array of strings to gameWords and set layout to use for the word
        String gameWords = "algorithm analog app application array backup bandwidth binary bit bitmap bite blog blogger bookmark boot broadband browser buffer bug bus byte cache captcha client clipart clipboard command compile compress computer configure cookie copy cybercrime cyberspace dashboard data database debug decompress delete desktop development digital disk document domain dot download drag dynamic email emoticon encrypt encryption enter exabyte file finder firewall firmware flaming flash flowchart folder font format frame freeware gigabyte graphics hack hacker hardware host hyperlink hypertext icon inbox integer interface Internet iteration Java joystick kernel key keyboard keyword laptop link login logic lurking mainframe macro malware media memory mirror modem monitor motherboard mouse multimedia net network node notebook offline online option output page password paste path phishing piracy pirate platform podcast portal print printer privacy process program programmer protocol queue reboot resolution restore root router time save scan scanner screen screenshot script scroll security server shareware shell shift snapshot software spam spammer spreadsheet storage spyware supercomputer surf syntax table tag template terabyte teminal thread toolbar trash typeface undo Unix upload username user utility version virtual virus web webmaster website widget window wireless wiki workstation worm zip";
        Log.d("JSLOG", "Number of words: " + gameWords.length());
        String [] gameWordsArray = gameWords.split(" ");

        LinearLayout wordLayout = (LinearLayout)findViewById(R.id.layoutLetters);

        // creates a random number based on the amount of strings in the gameWords array and then
        // assigns the current game word from random word
        int randomNumber = (int) (Math.random() * gameWordsArray.length);
        Log.d("JSLOG", "Random Number: " + randomNumber);
        currentGameWord = gameWordsArray[randomNumber];

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
            letterViews[c].setTextColor(Color.TRANSPARENT);
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

        aLetter = Character.toLowerCase(aLetter); // force character to lowercase
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
                letterViews[i].setTextColor(Color.WHITE);
                Toast.makeText(this, "Letter " + aLetter + " found!", Toast.LENGTH_SHORT).show();
                Log.d(JSLOG, "Player found letter: " + aLetter);

                // increase points
                points = points + 1; // one point scored for each correct letter in word
                Log.d(JSLOG, "Points increased to " + points);
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
            // set boolean winner to true and increase score
            winner = true;

            // show a toast and log winner
            Toast.makeText(this, "You are Winner! Ha ha ha.", Toast.LENGTH_SHORT).show();
            Log.d(JSLOG, "We have a winner! Word " + currentGameWord + " was solved!");

            // start the game over activity
            gameOver();
        }

        // check for loser
        if (letterMissed == 5) {

            // show a toast and log loser
            Toast.makeText(this, "You are Loser! Ha ha ha.", Toast.LENGTH_SHORT).show();
            Log.d(JSLOG, "We have a loser! Word " + currentGameWord + " was not solved!");

            // start the game over activity
            gameOver();
        }
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

    private void gameOver() {

        // create the intent
        Intent intent = new Intent(this, GameOverActivity.class);

        // send data with the intent using putExtra
        intent.putExtra("POINTSID", points); // sending points
        intent.putExtra("GAMEWORD", currentGameWord); // sending game word
        intent.putExtra("WINNER", winner); // sending boolean winner

        // start the activity
        startActivity(intent);

    }
}
