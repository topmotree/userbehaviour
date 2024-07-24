package dev.martisv.userbehaviour.tracker;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dev.martisv.userbehaviour.tracker.clickhandler.TrackerClickEventHandler;
import dev.martisv.userbehaviour.tracker.clickhandler.MutliClickEventHandler;
import dev.martisv.userbehaviour.tracker.clickhandler.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.DataCollector;
import dev.martisv.userbehaviour.tracker.datacollector.useraction.UserActionDataCollector;
import dev.martisv.userbehaviour.tracker.datacollector.view.metainfo.ViewMetaInfoDictionary;
import dev.martisv.userbehaviour.tracker.datacollector.view.ViewElementsDataCollector;
import dev.martisv.userbehaviour.tracker.activitylifecycle.AppActivityLifecycleCallback;
import dev.martisv.userbehaviour.tracker.repository.UserActivitySnapshotRepository;
import dev.martisv.userbehaviour.tracker.utils.ScreenSizeContainer;
import dev.martisv.userbehaviour.tracker.utils.TrackerOptions;

public class UserBehaviourTracker {
    private static TrackerOptions options = new TrackerOptions(true, false, false);

    private final Application app;
    private final MutliClickEventHandler multiTouchClickListener;
    private final ViewElementsDataCollector viewElementsDataCollector;
    private final UserActionDataCollector userActionDataCollector;
    private final ScreenSizeContainer screenSizeContainer;
    private final List<DataCollector> dataCollectors = new ArrayList<>();

    private UserBehaviourTracker(Builder builder) {
        this.app = builder.application;
        options = builder.options;

        this.viewElementsDataCollector = new ViewElementsDataCollector(app, builder.viewMetaDictionary);
        this.userActionDataCollector = new UserActionDataCollector(app, viewElementsDataCollector);
        this.multiTouchClickListener = new MutliClickEventHandler(app, new TrackerClickHandler());
        this.screenSizeContainer = new ScreenSizeContainer(app);

        addDataCollectors(builder);
        startActivityLifecycleObserving();
    }

    private void addDataCollectors(Builder builder) {
        dataCollectors.addAll(List.of(viewElementsDataCollector, userActionDataCollector));
        dataCollectors.addAll(builder.dataCollectors);
    }

    private class TrackerClickHandler implements TrackerClickEventHandler {
        @Override
        synchronized public void onClick(TouchCoordinates touchCoordinates) {
            userActionDataCollector.saveUserClick(touchCoordinates);
            UserActivitySnapshotRepository.save(screenSizeContainer, dataCollectors);
        }
    }

    private void startActivityLifecycleObserving() {
        AppActivityLifecycleCallback activityLifecycleCallback =
                new AppActivityLifecycleCallback(viewElementsDataCollector, multiTouchClickListener);

        app.registerActivityLifecycleCallbacks(activityLifecycleCallback);
    }

    public static TrackerOptions getOptions() {
        return options;
    }

    public static class Builder {
        private final Application application;
        private ViewMetaInfoDictionary viewMetaDictionary;
        private final List<DataCollector> dataCollectors = new ArrayList<>();
        private TrackerOptions options = UserBehaviourTracker.getOptions();

        public Builder(Application application) {
            this.application = application;
        }

        public Builder setMetaDictionary(ViewMetaInfoDictionary viewMetaDictionary) {
            this.viewMetaDictionary = viewMetaDictionary;
            return this;
        }

        public Builder addDataCollector(DataCollector dataCollector) {
            dataCollectors.add(dataCollector);
            return this;
        }

        public Builder setOptions(TrackerOptions options) {
            this.options = options;
            return this;
        }

        public UserBehaviourTracker build() {
            return new UserBehaviourTracker(this);
        }
    }
}
