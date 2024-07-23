package dev.martisv.userbehaviour.tracker;

import dev.martisv.userbehaviour.tracker.dataprovider.click.ClickEventHandler;
import dev.martisv.userbehaviour.tracker.dataprovider.sensor.SensorDataProvider;
import dev.martisv.userbehaviour.tracker.dataprovider.viewmap.ViewMapDataProvider;

public class UserClickHandler implements ClickEventHandler {
    private ViewMapDataProvider viewMapDataProvider;
    private SensorDataProvider sensorDataProvider;
    public UserClickHandler(ViewMapDataProvider viewMapDataProvider, SensorDataProvider sensorDataProvider) {
        this.viewMapDataProvider = viewMapDataProvider;
        this.sensorDataProvider = sensorDataProvider;
    }


    @Override
    public void onClick(TouchCoordinates touchCoordinates) {

    }
}
