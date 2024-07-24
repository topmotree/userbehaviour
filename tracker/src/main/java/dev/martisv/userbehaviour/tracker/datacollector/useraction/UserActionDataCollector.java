package dev.martisv.userbehaviour.tracker.datacollector.useraction;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.martisv.userbehaviour.tracker.clickhandler.TouchCoordinates;
import dev.martisv.userbehaviour.tracker.datacollector.DataCollector;
import dev.martisv.userbehaviour.tracker.datacollector.useraction.sensor.SensorDataContainer;

public class UserActionDataCollector implements DataCollector {
    final SensorDataContainer sensorDataContainer;
    final ClickedElementFinder clickedElementFinder;

    List<UserAction> currentActions = new ArrayList<>();

    public UserActionDataCollector(Application app, ClickedElementFinder clickedElementFinder) {
        this.sensorDataContainer = new SensorDataContainer(app);
        this.clickedElementFinder = clickedElementFinder;
    }

    public void saveUserClick(TouchCoordinates clickCoordinates) {
        long clickTimestamp = System.currentTimeMillis();
        String clickedElementId = clickedElementFinder.findClickedElementId(clickCoordinates);
        UserAction userAction = new UserAction(clickedElementId, ActionType.CLICK, clickTimestamp, clickCoordinates, sensorDataContainer.getSensorData());
        currentActions = Collections.singletonList(userAction);
    }

    @Override
    public String getJsonKey() {
        return "user_actions";
    }

    @Override
    public JSONArray getJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (UserAction action : currentActions) {
            try {
                final JSONObject actionJson = action.toJson();
                jsonArray.put(actionJson);
            } catch (Exception e) {
                Log.e("UserActionDataCollector", "Error converting UserAction to JSON", e);
            }
        }
        return jsonArray;
    }
}
