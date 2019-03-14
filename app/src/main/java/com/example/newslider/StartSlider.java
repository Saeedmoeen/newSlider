package com.example.newslider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartSlider extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout layoutDots;
    Button btn_Skip, btn_Next;

    SliderPagerAdapter pagerAdapter;
    private SliderPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_slider);
        ChangeStatusBarColor();
        prefManager = new SliderPrefManager(this);
        if (!prefManager.startSlider()) {
            launchMainScreen();
            return;
        }
        viewPager = findViewById(R.id.View_Pager);
        layoutDots = findViewById(R.id.Layout_Dots);
        btn_Next = findViewById(R.id.btn_next);
        btn_Skip = findViewById(R.id.btn_skip);
        pagerAdapter = new SliderPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        showDots(viewPager.getCurrentItem());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                showDots(i);
                if (i == viewPager.getAdapter().getCount() - 1) {
                    btn_Skip.setVisibility(View.GONE);
                    btn_Next.setText(R.string.btn_Gotit);
                } else {
                    btn_Skip.setVisibility(View.VISIBLE);
                    btn_Next.setText(R.string.btn_Next);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curentPage = viewPager.getCurrentItem();
                int lastPage = viewPager.getAdapter().getCount() - 1;
                if (curentPage == lastPage) {
                    prefManager.setStartSlider(false);
                    launchMainScreen();
                } else {
                    viewPager.setCurrentItem(curentPage + 1);
                }
            }
        });

        btn_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMainScreen();
            }
        });

    }

    private void showDots(int pageNumber) {
        TextView[] dots = new TextView[viewPager.getAdapter().getCount()];
        layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#x00B7;"));
            dots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            dots[i].setTextColor(ContextCompat.getColor(this, (i == pageNumber ? R.color.dot_Active : R.color.dot_Incative)
            ));
            layoutDots.addView(dots[i]);
        }
    }

    private void launchMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void ChangeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
