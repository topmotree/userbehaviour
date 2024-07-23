package dev.martisv.userbehaviour.tracker;

import android.app.Application;
import android.view.View;

import dev.martisv.userbehaviour.tracker.data.dataprovider.click.MutliTouchEventHandler;
import dev.martisv.userbehaviour.tracker.data.dataprovider.click.UserClickHandler;
import dev.martisv.userbehaviour.tracker.data.dataprovider.metadictionary.MetaDictionary;
import dev.martisv.userbehaviour.tracker.data.dataprovider.sensor.SensorDataProvider;
import dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement.ViewElementsDataProvider;
import dev.martisv.userbehaviour.tracker.presentation.AppActivityLifecycleCallback;

public class UserBehaviourTracker {
    private final Application app;

    private final MutliTouchEventHandler multiTouchClickListener;
    private final MetaDictionary metaDictionary;
    private final UserClickHandler userClickHandler;
    private final ViewElementsDataProvider viewElementsDataProvider;
    private final SensorDataProvider sensorDataProvider;

    private UserBehaviourTracker(Builder builder) {
        this.app = builder.application;

        this.viewElementsDataProvider = new ViewElementsDataProvider(app);
        this.sensorDataProvider = new SensorDataProvider(app);
        this.userClickHandler = new UserClickHandler(viewElementsDataProvider, sensorDataProvider);

        this.multiTouchClickListener = new MutliTouchEventHandler(app, userClickHandler);
        this.metaDictionary = builder.metaDictionary;

        startActivityLifecycleObserving();
    }

    public MutliTouchEventHandler getTouchEventHandler() {
        return multiTouchClickListener;
    }

    public void saveView(View view) {
        viewElementsDataProvider.saveViewInfo(view);
    }

    private void startActivityLifecycleObserving() {
        AppActivityLifecycleCallback activityLifecycleCallback =
                new AppActivityLifecycleCallback(this);

        app.registerActivityLifecycleCallbacks(activityLifecycleCallback);
    }

    public static class Builder {
        private final Application application;
        private MetaDictionary metaDictionary;

        public Builder(Application application) {
            this.application = application;
        }

        public Builder setMetaDictionary(MetaDictionary metaDictionary) {
            this.metaDictionary = metaDictionary;
            return this;
        }

        public UserBehaviourTracker build() {
            return new UserBehaviourTracker(this);
        }
    }
}
