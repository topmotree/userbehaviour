package dev.martisv.userbehaviour.tracker;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class TouchCoordinates {
    private float x;
    private float y;

    public TouchCoordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public JSONObject toJson() {
        try {
            JSONObject json = new JSONObject();
            json.put("x", x);
            json.put("y", y);
            return json;
        } catch (JSONException e) {
            Log.e("TouchCoordinates", "Error creating JSON object", e);
            return null;
        }
    }
}
