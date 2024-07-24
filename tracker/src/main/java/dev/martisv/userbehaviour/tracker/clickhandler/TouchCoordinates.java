package dev.martisv.userbehaviour.tracker.clickhandler;

import org.json.JSONException;
import org.json.JSONObject;

public class TouchCoordinates {
    private int x;
    private int y;

    public TouchCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "TouchCoordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("x", x);
        json.put("y", y);
        return json;
    }
}
