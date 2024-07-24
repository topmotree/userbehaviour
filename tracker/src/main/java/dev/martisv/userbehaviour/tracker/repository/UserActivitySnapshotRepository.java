package dev.martisv.userbehaviour.tracker.repository;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dev.martisv.userbehaviour.tracker.UserBehaviourTracker;
import dev.martisv.userbehaviour.tracker.datacollector.DataCollector;
import dev.martisv.userbehaviour.tracker.utils.ScreenSizeContainer;

public class UserActivitySnapshotRepository {
    private static final List<JSONObject> userActivitySnapshots = new ArrayList<>();

    static public void save(ScreenSizeContainer screenSizeContainer, List<DataCollector> dataCollectors) {
        final JSONObject userActivitySnapshot = createUserActivitySnapshot(screenSizeContainer, dataCollectors);

        if (userActivitySnapshot != null && UserBehaviourTracker.getOptions().isPrintClicks()) {
            Log.d("UserActivitySnapshotRepository", "Clicked: " + userActivitySnapshot);
        }

        if (!userActivitySnapshots.isEmpty() && UserBehaviourTracker.getOptions().isPrintClickHistory()) {
            Log.d("UserActivitySnapshotRepository", "                  ");
            Log.d("UserActivitySnapshotRepository", "Click history: ");
            for (JSONObject snapshot : userActivitySnapshots) {
                Log.d("UserActivitySnapshotRepository", snapshot.toString());
                Log.d("UserActivitySnapshotRepository", "-----------------");
            }
        }
        userActivitySnapshots.add(userActivitySnapshot);
    }

    private static JSONObject createUserActivitySnapshot(ScreenSizeContainer screenSizeContainer, List<DataCollector> dataCollectors) {
        try {
            JSONObject json = new JSONObject();
            json.put("screen_width", screenSizeContainer.getScreenWidth());
            json.put("screen_height", screenSizeContainer.getScreenHeight());
            for (DataCollector dataCollector : dataCollectors) {
                json.put(dataCollector.getJsonKey(), dataCollector.getJsonArray());
            }

            return json;
        } catch (JSONException e) {
            Log.d("ScreenSnapshotDto", "Error creating JSON object");
            return null;
        }
    }
}
