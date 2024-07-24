package dev.martisv.userbehaviour.tracker.datacollector;

import org.json.JSONArray;

public interface DataCollector {
    public String getJsonKey();
    public JSONArray getJsonArray();
}
