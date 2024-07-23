package dev.martisv.userbehaviour.tracker;

import android.app.Activity;
import android.view.View;

import dev.martisv.userbehaviour.tracker.dataprovider.click.MutliTouchClickListener;
import dev.martisv.userbehaviour.tracker.dataprovider.metadictionary.MetaDictionary;
import dev.martisv.userbehaviour.tracker.dataprovider.sensor.SensorDataProvider;
import dev.martisv.userbehaviour.tracker.dataprovider.viewmap.ViewMapDataProvider;

public class UserBehaviourTracker {
    private MutliTouchClickListener multiTouchClickListener;
    private MetaDictionary metaDictionary;
    private UserClickHandler userClickHandler;
    private ViewMapDataProvider viewMapDataProvider;
    private SensorDataProvider sensorDataProvider;



    private UserBehaviourTracker(UserBehaviourTrackerBuilder builder) {


        this.viewMapDataProvider = new ViewMapDataProvider(builder.activity);
        this.sensorDataProvider = new SensorDataProvider(builder.activity);
        this.userClickHandler = new UserClickHandler(viewMapDataProvider, sensorDataProvider);

        this.multiTouchClickListener = new MutliTouchClickListener(builder.activity, userClickHandler);
        this.metaDictionary = builder.userBehaviourTracker.metaDictionary;
    }

    public View.OnTouchListener getTouchListener() {
        return multiTouchClickListener;
    }

    public void saveView(View view) {
        viewMapDataProvider.saveViewInfo(view);
    }

    public static class UserBehaviourTrackerBuilder {
        private final Activity activity;

        private UserBehaviourTracker userBehaviourTracker;

        public UserBehaviourTrackerBuilder(Activity activity) {
            this.activity = activity;
        }

        public UserBehaviourTrackerBuilder setMetaDictionary(MetaDictionary metaDictionary) {
            userBehaviourTracker.metaDictionary = metaDictionary;
            return this;
        }

        public UserBehaviourTracker build() {
            return new UserBehaviourTracker(this);
        }
    }
}
