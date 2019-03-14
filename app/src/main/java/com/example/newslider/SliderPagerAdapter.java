package com.example.newslider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SliderPagerAdapter extends PagerAdapter {

    String[] SlideTitles;
    String[] SlideDescription;
    int[] BgColor = {R.color.Slide_One, R.color.Slide_Two, R.color.Slide_Three, R.color.Slide_Four};
    int[] ImageIds = {R.drawable.ic_food, R.drawable.ic_movie, R.drawable.ic_discount, R.drawable.ic_travel};

    Context context;

    public SliderPagerAdapter(Context context) {
        this.context = context;
        SlideTitles = context.getResources().getStringArray(R.array.Slide_Title);
        SlideDescription = context.getResources().getStringArray(R.array.Slide_Description);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide, container, false);
        view.findViewById(R.id.Layout_Slider).setBackgroundColor(ContextCompat.getColor(context, BgColor[position]));
        ((ImageView) view.findViewById(R.id.Slid_Image)).setImageResource(ImageIds[position]);
        ((TextView) view.findViewById(R.id.Slid_Title)).setText(SlideTitles[position]);
        ((TextView) view.findViewById(R.id.Slid_Description)).setText(SlideDescription[position]);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return BgColor.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
