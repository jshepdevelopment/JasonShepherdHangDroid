package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Jason Shepherd on 11/5/2015.
 */
public class HighScoresActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // use the window manager to make fullscreen menu
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_high_scores);

        // access the shared preferences file to get the high score and name
        SharedPreferences hangDroidPrefs = getSharedPreferences("JSHangDroidPrefs", Context.MODE_PRIVATE);
        String nameData = hangDroidPrefs.getString("PLAYER_NAME_KEY", "Default");
        int pointsData = hangDroidPrefs.getInt("HIGH_SCORE_KEY", 0);

        // assign the views from layout xml
        TextView  scoreText = (TextView)findViewById(R.id.scoresView);
        TextView  pointsText = (TextView)findViewById(R.id.pointsView);

        // set the views to display the high score and name
        scoreText.setText(nameData);
        pointsText.setText(String.valueOf(pointsData));

    }

}
