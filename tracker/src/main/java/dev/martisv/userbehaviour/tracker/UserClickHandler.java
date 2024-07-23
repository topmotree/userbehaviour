package dev.martisv.userbehaviour.tracker;

import dev.martisv.userbehaviour.tracker.data.dataprovider.click.ClickEventHandler;
import dev.martisv.userbehaviour.tracker.data.dataprovider.sensor.SensorData;
import dev.martisv.userbehaviour.tracker.data.dataprovider.sensor.SensorDataProvider;
import dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement.ViewElement;
import dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement.ViewElementsDataProvider;
import dev.martisv.userbehaviour.tracker.data.saver.ScreenSnapshotSaver;

public class UserClickHandler implements ClickEventHandler {
    private ViewElementsDataProvider viewElementsDataProvider;
    private SensorDataProvider sensorDataProvider;

    public UserClickHandler(ViewElementsDataProvider viewElementsDataProvider, SensorDataProvider sensorDataProvider) {
        this.viewElementsDataProvider = viewElementsDataProvider;
        this.sensorDataProvider = sensorDataProvider;
    }

    @Override
    public void onClick(TouchCoordinates touchCoordinates) {
        ViewElement viewElement = viewElementsDataProvider.getCurrentViewElement();
        SensorData sensorData = sensorDataProvider.getSensorData();
        long clickTimestamp = System.currentTimeMillis();
        ScreenSnapshotSaver.saveScreenSnapshot(viewElement, touchCoordinates, sensorData, clickTimestamp);
    }
}
