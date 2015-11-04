package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Jason Shepherd on 11/4/2015.
 */
public class GameOverActivity extends AppCompatActivity {

    RelativeLayout gameOverLayout;

    String gameWord;
    TextView gameWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // get the layout used for updating background
        gameOverLayout = (RelativeLayout)findViewById(R.id.gameOverLayout);

        // get the points from the intent that started this activity. defaults to 0 if no data sent
        int points = getIntent().getIntExtra("POINTSID", 0);
        gameWord = getIntent().getStringExtra("GAMEWORD");
        boolean winner = getIntent().getBooleanExtra("WINNER", false); // false is loser by default

        // display game word
        gameWordView = (TextView)findViewById(R.id.gameWordView);
        gameWordView.setText(gameWord);

        // call method based on whether player is winner or loser
        if(winner) {
            displayWin();
        } else displayLoss();
    }

    private void displayWin() {

        // apply winner background accordingly
        gameOverLayout.setBackgroundResource(R.drawable.game_over_win);

        // set game word to green
        gameWordView.setTextColor(Color.GREEN);

    }

    private void displayLoss() {

        // set game word to red
        gameWordView.setTextColor(Color.RED);

    }

    public void playAgain(View view) {
        // Go back to the main menu
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
