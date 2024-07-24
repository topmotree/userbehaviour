package dev.martisv.userbehaviour.tracker.datacollector.useraction;

import org.json.JSONException;
import org.json.JSONObject;

import dev.martisv.userbehaviour.tracker.clickhandler.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.useraction.sensor.SensorData;

public class UserAction {
    private String elementId;
    private ActionType actionType;
    private long timestamp;
    private TouchCoordinates touchCoordinates;
    private SensorData sensorData;

    public UserAction(
            String elementId,
            ActionType actionType,
            long timestamp,
            TouchCoordinates touchCoordinates,
            SensorData sensorData
    ) {
        this.elementId = elementId;
        this.actionType = actionType;
        this.timestamp = timestamp;
        this.touchCoordinates = touchCoordinates;
        this.sensorData = sensorData;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        if (elementId == null) {
            json.put("element_id", JSONObject.NULL);
        } else {
            json.put("element_id", elementId);
        }
        json.put("action_type", actionType.getTitle());
        json.put("timestamp", timestamp);
        json.put("touch_coordinates", touchCoordinates.toJson());
        json.put("sensor_data", sensorData.toJson());
        return json;
    }
}

enum ActionType {
    CLICK("click");

    private final String title;

    ActionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

