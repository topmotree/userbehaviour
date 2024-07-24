package dev.martisv.userbehaviour.tracker.utils;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenSizeContainer {
    private final int screenWidth;
    private final int screenHeight;

    public ScreenSizeContainer(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
