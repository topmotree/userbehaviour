package dev.martisv.userbehaviour.tracker.data.saver;

import android.util.Log;

import java.util.List;

import dev.martisv.userbehaviour.tracker.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.data.dataprovider.sensor.SensorData;
import dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement.ViewElement;
import dev.martisv.userbehaviour.tracker.data.saver.dto.ScreenSnapshotDto;

public class ScreenSnapshotSaver {

    public static void saveScreenSnapshot(ViewElement screenElement, TouchCoordinates clickCoordinates, SensorData sensorData, long clickTimestamp) {
        ViewElement clickedElement = screenElement.findChildElement(clickCoordinates);
        List<ViewElement> screenElements = screenElement.getChildElements();
        ScreenSnapshotDto screenSnapshotDto = new ScreenSnapshotDto(screenElement.getWidth(), screenElement.getHeight(), screenElements, clickCoordinates, clickedElement, clickTimestamp, sensorData);

        Log.d("ScreenSnapshotSaver", screenSnapshotDto.toJson().toString());

        //Possible to saving of the screen snapshot
    }


}
