package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Jason Shepherd on 10/30/2015.
 */

public class DroidSelectActivity extends Activity {

    ViewPager viewPager;
            PagerAdapter adapter;
            int[] headImages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Get the view from viewpager_main.xml
            setContentView(R.layout.viewpager_main);


            headImages = new int[] { R.drawable.head1, R.drawable.head2,
            R.drawable.head3, R.drawable.head4,
            R.drawable.head5 };

            // Locate the ViewPager in viewpager_main.xml
            viewPager = (ViewPager) findViewById(R.id.pager);
            // Pass results to ViewPagerAdapter Class
            adapter = new ViewPagerAdapter(DroidSelectActivity.this, headImages);
            // Binds the Adapter to the ViewPager
            viewPager.setAdapter(adapter);

    }

}
