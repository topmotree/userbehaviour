package dev.martisv.userbehaviour.tracker.data.saver.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class TouchCoordinatesDto {
    private float x;
    private float y;

    public TouchCoordinatesDto(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        return json;
    }
}
