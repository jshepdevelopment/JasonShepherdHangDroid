package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jason Shepherd on 11/4/2015.
 */
public class GameOverActivity extends AppCompatActivity {

    String gameWord;
    TextView gameWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // get the points from the intent that started this activity. defaults to 0 if no data sent
        int points = getIntent().getIntExtra("POINTSID", 0);
        gameWord = getIntent().getStringExtra("GAMEWORD");
        boolean winner = getIntent().getBooleanExtra("WINNER", false); // false is loser by default

        // display game word
        gameWordView = (TextView)findViewById(R.id.gameWordView);
        gameWordView.setText(gameWord);

        // call method based on whether player is winner or loser

    }

    private void displayWin() {

        // set game word to green color if winner

    }

    private void displayLoss() {

        // set game word to red color if loser

    }

    private void playAgain() {

    }

    private void exitGame() {
        //
    }


}
