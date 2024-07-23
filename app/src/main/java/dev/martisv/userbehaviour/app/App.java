package dev.martisv.userbehaviour.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;
import dev.martisv.userbehaviour.tracker.presentation.TrackerWindowCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new UserBehaviourTracker.Builder(this).build();
    }
}