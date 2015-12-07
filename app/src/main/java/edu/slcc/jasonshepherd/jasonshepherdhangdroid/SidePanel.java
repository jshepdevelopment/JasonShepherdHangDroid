package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.slcc.jasonshepherd.jasonshepherdhangdroid.R;

/**
 * Created by Jason Shepherd on 12/06/2015.
 */
public class SidePanel extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.side_fragment, container, false);


        // access the shared preferences file to get the high score and name
        SharedPreferences hangDroidPrefs = this.getActivity().getSharedPreferences("JSHangDroidPrefs", Context.MODE_PRIVATE);
        String nameData = hangDroidPrefs.getString("PLAYER_NAME_KEY", "Default");
        int pointsData = hangDroidPrefs.getInt("HIGH_SCORE_KEY", 0);

        // assign the views from layout xml
        TextView scoreText = (TextView)v.findViewById(R.id.scoresView);
        TextView  pointsText = (TextView)v.findViewById(R.id.pointsView);

        // set the views to display the high score and name
        scoreText.setText(nameData);
        pointsText.setText(String.valueOf(pointsData));


        return v;


    }
}

