package dev.martisv.userbehaviour.tracker.converter;

import android.util.Log;

import java.util.List;

import dev.martisv.userbehaviour.tracker.datacollector.click.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.sensor.SensorData;
import dev.martisv.userbehaviour.tracker.datacollector.view.ViewElement;
import dev.martisv.userbehaviour.tracker.converter.dto.ScreenSnapshotDto;

public class ScreenSnapshotDtoConverter {

    public static ScreenSnapshotDto convert(ViewElement screenElement, TouchCoordinates clickCoordinates, SensorData sensorData, long clickTimestamp) {
        ViewElement clickedElement = screenElement.findChildElement(clickCoordinates);
        List<ViewElement> screenElements = screenElement.getChildElements();
        return new ScreenSnapshotDto(screenElement.getWidth(), screenElement.getHeight(), screenElements, clickCoordinates, clickedElement, clickTimestamp, sensorData);
    }


}
