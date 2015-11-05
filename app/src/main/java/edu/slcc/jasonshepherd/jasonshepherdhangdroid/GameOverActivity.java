package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by Jason Shepherd on 11/4/2015.
 */
public class GameOverActivity extends AppCompatActivity {

    // define the layout, so we can change background depending on win or loss
    RelativeLayout gameOverLayout;

    // variables needed to display game word and high score
    String gameWord;
    TextView gameWordView;
    TextView gamePointsView;


    // variable for application Shared Preferences
    private SharedPreferences hangDroidPrefs;

    // points
    int points;

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

        gamePointsView = (TextView)findViewById((R.id.gamePointsView));
        gamePointsView.setText(String.valueOf(points)); // this is a sweet and easy way

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

        // set the high score
        setHighScore();
        showHighScore();

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

    private void setHighScore() {

        // set the high score
        SharedPreferences.Editor highScoreEditor = hangDroidPrefs.edit();

        highScoreEditor.putString("PLAYER_NAME_KEY", "Anonymous");
        highScoreEditor.putInt("HIGH_SCORE_KEY" , points);
        highScoreEditor.commit();

    }

    private void showHighScore() {

    }

}
