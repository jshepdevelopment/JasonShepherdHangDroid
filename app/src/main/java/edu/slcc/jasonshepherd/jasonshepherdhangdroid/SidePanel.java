package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.slcc.jasonshepherd.jasonshepherdhangdroid.R;

/**
 * Created by Jason Shepherd on 12/06/2015.
 */
public class SidePanel extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_high_scores, container, false);
    }
}
