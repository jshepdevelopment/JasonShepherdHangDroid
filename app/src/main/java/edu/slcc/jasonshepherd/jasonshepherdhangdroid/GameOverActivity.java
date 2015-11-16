package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jason Shepherd on 11/4/2015.
 */

public class GameOverActivity extends AppCompatActivity {

    // define the layout, so we can change background depending on win or loss
    RelativeLayout gameOverLayout;

    // variables and views needed to display game word, points and win/loss message
    String gameWord;
    TextView gameWordView;
    TextView gamePointsView;
    TextView gameLoseWin;

    // variable for application Shared Preferences for storing points and name
    SharedPreferences hangDroidPrefs;

    // variables to store points and player name for high score
    int points;
    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // instantiating the shared preferences object
        hangDroidPrefs = getSharedPreferences("JSHangDroidPrefs", 0);

        // get the layout used for updating background
        gameOverLayout = (RelativeLayout)findViewById(R.id.gameOverLayout);

        // get the points from the intent that started this activity. defaults to 0 if no data sent
        points = getIntent().getIntExtra("POINTSID", 0);
        gameWord = getIntent().getStringExtra("GAMEWORD");
        boolean winner = getIntent().getBooleanExtra("WINNER", false); // false is loser by default

        // display game word and points
        gameWordView = (TextView)findViewById(R.id.gameWordView);
        gameWordView.setText(gameWord);

        // display game word and points
        gameLoseWin = (TextView)findViewById(R.id.gameLoseWin);
        gamePointsView = (TextView)findViewById((R.id.gamePointsView));
        gamePointsView.setText(String.valueOf(points)); // this is a sweet and easy way

        // get values from shared prefs file to determine if new high score is higher than old high
        SharedPreferences getHangDroidPrefs = getSharedPreferences("JSHangDroidPrefs", Context.MODE_PRIVATE);
        int prefsPointsData = getHangDroidPrefs.getInt("HIGH_SCORE_KEY", 0);

        // add the high score if it is more than the high score in the prefs file
        if(prefsPointsData < points) {
            setHighScore();
        }

        // call method based on whether player is winner or loser
        if(winner) {
            displayWin();
        } else displayLoss();
    }

    // method to use if game was won
    private void displayWin() {
        // apply winner background accordingly
        gameOverLayout.setBackgroundResource(R.drawable.game_over_win);
        // add congrats to winner
        gameLoseWin.setText("Congrats!");
        // set game word to green
        gameWordView.setTextColor(Color.GREEN);
   }

    // method to use if game was lost
    private void displayLoss() {
        // set game word to red
        gameWordView.setTextColor(Color.RED);
    }

    // method to play game again
    public void playAgain(View view) {
        // Go back to the main menu
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setHighScore() {
        // Display High Score Dialog by creating an alert dialog called winBuild
        AlertDialog.Builder dialogBuild = new AlertDialog.Builder(this);
        dialogBuild.setTitle("Congrats!");
        dialogBuild.setMessage("You've taken the high score with " + points + " points!");

        //set up a text field for input
        final EditText nameInput= new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        nameInput.setInputType(InputType.TYPE_CLASS_TEXT);
        dialogBuild.setView(nameInput);

        // save the high score when user selects ok button
        dialogBuild.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                playerName = nameInput.getText().toString();
                // set the high score by accessing the shared prefs file
                SharedPreferences.Editor highScoreEditor = hangDroidPrefs.edit();
                highScoreEditor.putString("PLAYER_NAME_KEY", playerName);
                highScoreEditor.putInt("HIGH_SCORE_KEY", points);
                highScoreEditor.apply();
                // log the high score for debugging
                Log.d("JSLOG", "High score logged as " + playerName + " " + points);
            }
        });

        // show the dialog after building it
        dialogBuild.show();
    }

}
