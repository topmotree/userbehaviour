package dev.martisv.userbehaviour.tracker.converter.dto;

import org.json.JSONException;
import org.json.JSONObject;

import dev.martisv.userbehaviour.tracker.clickhandler.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.sensor.SensorData;
import dev.martisv.userbehaviour.tracker.datacollector.view.ViewElement;

public class UserActionDto {
    private String elementId;
    private String actionType;
    private long timestamp;
    private TouchCoordinatesDto touchCoordinates;
    private SensorDataDto sensorData;

    public UserActionDto(
            String elementId,
            String actionType,
            long timestamp,
            TouchCoordinatesDto touchCoordinates,
            SensorDataDto sensorData
    ) {
        this.elementId = elementId;
        this.actionType = actionType;
        this.timestamp = timestamp;
        this.touchCoordinates = touchCoordinates;
        this.sensorData = sensorData;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("element_id", elementId);
        json.put("action_type", actionType);
        json.put("timestamp", timestamp);
        json.put("touch_coordinates", touchCoordinates.toJson());
        json.put("sensor_data", sensorData.toJson());
        return json;
    }

    public static UserActionDto fromClickedData(ViewElement clickedElement, long clickTimestamp, TouchCoordinates clickCoordinates, SensorData sensorData) {
        String elementId = "";
        if (clickedElement != null) {
            elementId = clickedElement.getElementId();
        }

        return new UserActionDto(
                elementId,
                "click",
                clickTimestamp,
                new TouchCoordinatesDto(clickCoordinates.getX(), clickCoordinates.getY()),
                SensorDataDto.fromSensorData(sensorData)
        );
    }
}
