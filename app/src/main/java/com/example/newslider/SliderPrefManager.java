package com.example.newslider;

import android.content.Context;
import android.content.SharedPreferences;

public class SliderPrefManager {

    private Context context;
    private SharedPreferences preferences;

    private static final String PREF_NAME = "Slider_Pref";
    private static final String KEY_START = "Start_Slider";

    public SliderPrefManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public Boolean startSlider() {
        return preferences.getBoolean(KEY_START, true);
    }

    public void setStartSlider(Boolean start) {
        preferences.edit().putBoolean(KEY_START, start).apply();
    }
}
