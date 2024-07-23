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

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleListener());


    }
}

class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        final View rootView = activity.findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnDrawListener(() -> {
            JSONArray jsonArray =  ScreenMapUtils.collectScreenElements(rootView);
            Log.d("ActivityLifecycleListener", "APP addOnDrawListener: " + jsonArray.toString());
        });

        final Window win = activity.getWindow();
        final Window.Callback localCallback = win.getCallback();
        final TouchEventHandler touchEventHandler = new TouchEventHandler(activity);

        win.setCallback(new MyWindowCallback(localCallback, touchEventHandler));
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d("ActivityLifecycleListener", "APP onActivityStarted: " + activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d("ActivityLifecycleListener", "APP onActivityResumed: " + activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

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
