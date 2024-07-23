package dev.martisv.userbehaviour.tracker.presentation;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;

public class AppActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    final UserBehaviourTracker tracker;
    View rootView;
    ViewTreeObserver.OnDrawListener listener;

    public AppActivityLifecycleCallback(UserBehaviourTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        final Window activityWindow = activity.getWindow();
        final Window.Callback localCallback = activityWindow.getCallback();
        activityWindow.setCallback(new TrackerWindowCallback(localCallback, tracker));
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        rootView = activity.findViewById(android.R.id.content);
        listener = () -> {
            tracker.saveView(rootView);
        };
        rootView.getViewTreeObserver().addOnDrawListener(listener);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        rootView.getViewTreeObserver().removeOnDrawListener(listener);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
