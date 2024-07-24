package dev.martisv.userbehaviour.tracker;

import android.app.Application;
import android.util.Log;

import dev.martisv.userbehaviour.tracker.android.touch.ClickEventHandler;
import dev.martisv.userbehaviour.tracker.converter.dto.ScreenSnapshotDto;
import dev.martisv.userbehaviour.tracker.datacollector.click.MutliTouchDataCollector;
import dev.martisv.userbehaviour.tracker.datacollector.click.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.view.metainfo.ViewMetaInfoDictionary;
import dev.martisv.userbehaviour.tracker.datacollector.sensor.SensorData;
import dev.martisv.userbehaviour.tracker.datacollector.sensor.SensorDataCollector;
import dev.martisv.userbehaviour.tracker.datacollector.view.ViewElement;
import dev.martisv.userbehaviour.tracker.datacollector.view.ViewElementsDataCollector;
import dev.martisv.userbehaviour.tracker.converter.ScreenSnapshotDtoConverter;
import dev.martisv.userbehaviour.tracker.android.activity.AppActivityLifecycleCallback;

public class UserBehaviourTracker {
    private final Application app;
    private final MutliTouchDataCollector multiTouchClickListener;
    private final ViewElementsDataCollector viewElementsDataCollector;
    private final SensorDataCollector sensorDataCollector;

    private UserBehaviourTracker(Builder builder) {
        this.app = builder.application;
        this.viewElementsDataCollector = new ViewElementsDataCollector(app, builder.viewMetaInfoDictionary);
        this.sensorDataCollector = new SensorDataCollector(app);
        this.multiTouchClickListener = new MutliTouchDataCollector(app, new ClickHandler());
        startActivityLifecycleObserving();
    }

    private class ClickHandler implements ClickEventHandler {
        @Override
        public void onClick(TouchCoordinates touchCoordinates) {
            ViewElement viewElement = viewElementsDataCollector.getCurrentViewElement();
            SensorData sensorData = sensorDataCollector.getSensorData();
            long clickTimestamp = System.currentTimeMillis();

            final ScreenSnapshotDto dto = ScreenSnapshotDtoConverter.convert(viewElement, touchCoordinates, sensorData, clickTimestamp);

            // Next step: send dto to server. Currently just logging it.
            Log.d("UserBehaviourTracker", "onClick: " + dto.toJson().toString());
        }
    }

    private void startActivityLifecycleObserving() {
        AppActivityLifecycleCallback activityLifecycleCallback =
                new AppActivityLifecycleCallback(viewElementsDataCollector, multiTouchClickListener);

        app.registerActivityLifecycleCallbacks(activityLifecycleCallback);
    }

    public static class Builder {
        private final Application application;
        private ViewMetaInfoDictionary viewMetaInfoDictionary;

        public Builder(Application application) {
            this.application = application;
        }

        public Builder setMetaDictionary(ViewMetaInfoDictionary viewMetaInfoDictionary) {
            this.viewMetaInfoDictionary = viewMetaInfoDictionary;
            return this;
        }

        public UserBehaviourTracker build() {
            return new UserBehaviourTracker(this);
        }
    }
}
