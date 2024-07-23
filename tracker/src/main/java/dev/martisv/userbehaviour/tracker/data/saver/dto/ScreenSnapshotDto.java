package dev.martisv.userbehaviour.tracker.data.saver.dto;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dev.martisv.userbehaviour.tracker.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.data.dataprovider.sensor.SensorData;
import dev.martisv.userbehaviour.tracker.data.dataprovider.viewelement.ViewElement;

public class ScreenSnapshotDto {
    private int screenWidth;
    private int screenHeight;
    private List<ElementDto> elements;
    private List<UserActionDto> userActions;

    public ScreenSnapshotDto(
            int screenWidth,
            int screenHeight,
            List<ViewElement> screenElements,
            TouchCoordinates clickCoordinates,
            ViewElement clickedElement,
            long clickTimestamp,
            SensorData sensorData
    ) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        elements = ElementDto.fromViewElements(screenElements);
        userActions = Collections.singletonList(UserActionDto.fromClickedData(clickedElement, clickTimestamp, clickCoordinates, sensorData));
    }

    public JSONObject toJson() {
        try {
            JSONObject json = new JSONObject();
            json.put("screen_width", screenWidth);
            json.put("screen_height", screenHeight);

            JSONArray elementsJson = new JSONArray();
            for (ElementDto element : elements) {
                elementsJson.put(element.toJson());
            }
            json.put("elements", elementsJson);

            JSONArray userActionsJson = new JSONArray();
            for (UserActionDto action : userActions) {
                userActionsJson.put(action.toJson());
            }
            json.put("user_actions", userActionsJson);

            return json;
        } catch (JSONException e) {
            Log.d("ScreenSnapshotDto", "Error creating JSON object");
            return null;
        }

    }
}
