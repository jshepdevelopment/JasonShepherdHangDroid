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

    // string to store the game word
    public String currentGameWord;

    // textview array needed to assign textViews, one for each letter of word
    private TextView[] letterViews;

    // imageview used to update hangdroid graphics
    ImageView hangDroidView;

    // track to see if a letter was found in the word, or if it was missed and points
    int letterGuessed;  // track how many letters guessed
    int letterMissed;   // track how many letters missed
    int points = 0;     // track the score as points
    boolean winner = false; // track whether a winner or loser
    boolean multiPlayer = false; // used to check if this is a multiplayer game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // get multiplayer flag if game started as multiplayer, defaults to single player
        multiPlayer = getIntent().getBooleanExtra("MULTIPLAYER_FLAG", false);

        // set the game word as the guess word value if the game was started as multiplayer
        if(multiPlayer) currentGameWord = getIntent().getStringExtra("GUESS_WORD");

        // setGameWord method accepts an argument of type boolean, which is boolean multiPlayer
        setGameWord(multiPlayer);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflating the menu to add items to action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle the action bar button presses
         int id = item.getItemId();

        // action to call when refresh selected
        if (id == R.id.action_refresh) {
            setGameWord(false); // setGameWord multiplayer argument is false on new word select
            return true;
        }

        // action to call when display about selected
        if (id == R.id.action_about) {
            displayAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // method to set game word will set a new game word if the game is a single player game
    private void setGameWord(boolean multiPlayer) {

        // set up the views and word layout
        setContentView(R.layout.activity_game);
        hangDroidView = (ImageView)findViewById(R.id.hangDroidView);
        LinearLayout wordLayout = (LinearLayout) findViewById(R.id.layoutLetters);

        // only set a word if the multiplayer flag is false, otherwise the word was already set
        if(!multiPlayer) {

            // assign an array of strings to gameWords and set layout to use for the word
            String gameWords = "algorithm analog app application array backup bandwidth binary bit bitmap bite blog blogger bookmark boot broadband browser buffer bug bus byte cache captcha client clipart clipboard command compile compress computer configure cookie copy cybercrime cyberspace dashboard data database debug decompress delete desktop development digital disk document domain dot download drag dynamic email emoticon encrypt encryption enter exabyte file finder firewall firmware flaming flash flowchart folder font format frame freeware gigabyte graphics hack hacker hardware host hyperlink hypertext icon inbox integer interface Internet iteration Java joystick kernel key keyboard keyword laptop link login logic lurking mainframe macro malware media memory mirror modem monitor motherboard mouse multimedia net network node notebook offline online option output page password paste path phishing piracy pirate platform podcast portal print printer privacy process program programmer protocol queue reboot resolution restore root router time save scan scanner screen screenshot script scroll security server shareware shell shift snapshot software spam spammer spreadsheet storage spyware supercomputer surf syntax table tag template terabyte teminal thread toolbar trash typeface undo Unix upload username user utility version virtual virus web webmaster website widget window wireless wiki workstation worm zip";
            Log.d("JSLOG", "Number of words: " + gameWords.length());
            String[] gameWordsArray = gameWords.split(" ");

            // creates a random number based on the amount of strings in the gameWords array and then
            // assigns the current game word from random word
            int randomNumber = (int) (Math.random() * gameWordsArray.length);
            Log.d("JSLOG", "Random Number: " + randomNumber);
            currentGameWord = gameWordsArray[randomNumber];
        }

        // array to store text views for each letter of current word and the underscores
        letterViews = new TextView[currentGameWord.length()];

        // get rid of the text views in the layout, because new views are needed for new word
        wordLayout.removeAllViews();

        // go through each letter of current word to assign to the text views
        for (int c = 0; c < currentGameWord.length(); c++) {

            // make a textview for each letter
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

        // send a toast to inform player(s) that the new word is ready
        Toast.makeText(this, "A new word is ready.", Toast.LENGTH_SHORT).show();

        // log the current game word for debugging
        Log.d("JSLOG", "Game word " + currentGameWord + " set successfully.");

       }

    // method that is used when a letter is selected
    public void checkLetter(View view) {

        // first hide the button that was just pressed then check which letter the user has pressed
        // by looking at the letter in the textview then store as char and log
        view.setVisibility(View.GONE);
        String letterString = ((TextView)view).getText().toString();
        char aLetter = letterString.charAt(0);

        // force character to lowercase then log letter selected
        aLetter = Character.toLowerCase(aLetter);
        Log.d("JSLOG", "Player selected letter: " + aLetter);

        // used to track whether a letter has been guessed correctly or missed
        boolean letterGuessedFlag = false;
        boolean letterMissedFlag = false;

        // loop will check if the letter guessed matches a letter in the word then log for debug
        for(int i = 0; i < currentGameWord.length(); i++) {

            // Has guessed correctly
            if (currentGameWord.charAt(i) == aLetter) {
                letterGuessedFlag = true;
                // necessary to increment because some words have the same letter more than once
                letterGuessed++;

                // show the corresponding letter when the letter matches the guess then logs
                letterViews[i].setTextColor(Color.WHITE);
                Toast.makeText(this, "Letter " + aLetter + " found!", Toast.LENGTH_SHORT).show();
                Log.d("JSLOG", "Player found letter: " + aLetter);

                // increase points and log for debug
                points = points + 1; // one point scored for each correct letter in word
                Log.d("JSLOG", "Points increased to " + points);
            }
        }

        // update flags for letters guessed correctly
        if (letterGuessedFlag) {
            letterGuessedFlag = false;
            Log.d("JSLOG", "Player has correctly guessed " + letterGuessed + " letters.");
        } else letterMissedFlag = true;

        // increment counter when a letter is missed, necessary to track when a game is lost
        if (letterMissedFlag) {
            letterMissedFlag = false;
            letterMissed++;
            // let user know via toast and then log for debug
            Toast.makeText(this, "There is no " + aLetter + "! Try again!", Toast.LENGTH_SHORT).show();
            Log.d("JSLOG", "Player has missed " + letterMissed + " times.");
        }

        // display image based on missed guesses
        if (letterMissed == 1)hangDroidView.setImageResource(R.drawable.hangdroid_1);
        if (letterMissed == 2)hangDroidView.setImageResource(R.drawable.hangdroid_2);
        if (letterMissed == 3)hangDroidView.setImageResource(R.drawable.hangdroid_3);
        if (letterMissed == 4)hangDroidView.setImageResource(R.drawable.hangdroid_4);
        if (letterMissed == 5)hangDroidView.setImageResource(R.drawable.hangdroid_5);

        // check for winner by comparing number of correct guesses equal to word length
        if (letterGuessed == currentGameWord.length()) {
            // set boolean winner to true
            winner = true;
            // show a toast and log winner
            Toast.makeText(this, "You are Winner! Ha ha ha.", Toast.LENGTH_SHORT).show();
            Log.d("JSLOG", "We have a winner! The word ***" + currentGameWord + "*** was solved!");
            // start the game over activity
            gameOver();
        }

        // check for loser if loser has guessed five times without guessing all letters in word
        if (letterMissed == 5) {
            // show a toast and log loser
            Toast.makeText(this, "You are Loser! Ha ha ha.", Toast.LENGTH_SHORT).show();
            Log.d("JSLOG", "We have a loser! The word ***" + currentGameWord + "*** was not solved!");
            // start the game over activity
            gameOver();
        }
    }

    // a simple about program dialog box method
    private void displayAbout() {
        // create an AlertDialog builder object called dialogBuild
        AlertDialog.Builder dialogBuild = new AlertDialog.Builder(this);
        //set the dialog title and a message
        dialogBuild.setTitle("JS HangDroid");
        dialogBuild.setMessage("A hangman game.\nDeveloped for Buhler's CSIS 2630.\nVersion 1.0");

        // adds a button to the dialog only one needed for this dialog
        dialogBuild.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // there's nothing that needs to happen here so the dialog will just close
                        // and return to the game
                    }
                });

        // show the dialog box after building it
        dialogBuild.show();
    }

    // method to call when  the game is over
    private void gameOver() {

        // create the intent to start the activity
        Intent intent = new Intent(this, GameOverActivity.class);

        // send points, gameword, and winner data with the intent using putExtra
        intent.putExtra("POINTSID", points); // sending points
        intent.putExtra("GAMEWORD", currentGameWord); // sending game word
        intent.putExtra("WINNER", winner); // sending boolean winner

        // start the activity
        startActivity(intent);

    }
}
