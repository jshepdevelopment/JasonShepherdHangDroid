package edu.slcc.jasonshepherd.jasonshepherdhangdroid;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jason Shepherd on 10/30/2015.
 */

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    int[] headSelect;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, int[] headSelect) {
        this.context = context;
        this.headSelect = headSelect;
    }

    @Override
    public int getCount() {
        //return count.length;
        return headSelect.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        ImageView headSelectImage;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_droid_select, container,
                false);

        // Locate the ImageView in
        headSelectImage = (ImageView) itemView.findViewById(R.id.headSelectImage);
        // Capture position and set to the ImageView
        headSelectImage.setImageResource(headSelect[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
