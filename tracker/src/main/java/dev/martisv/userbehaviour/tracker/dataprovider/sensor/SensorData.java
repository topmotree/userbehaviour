package dev.martisv.userbehaviour.tracker.dataprovider.sensor;

import android.util.Log;

import org.json.JSONObject;

public class SensorData {
    private float[] accelerometerData;
    private float[] gyroscopeData;

    public void setAccelerometerData(float[] accelerometerData) {
        this.accelerometerData = accelerometerData;
    }

    public void setGyroscopeData(float[] gyroscopeData) {
        this.gyroscopeData = gyroscopeData;
    }

    public float[] getAccelerometerData() {
        return accelerometerData;
    }

    public float[] getGyroscopeData() {
        return gyroscopeData;
    }

    public JSONObject toJson() {
        try {
            JSONObject json = new JSONObject();
            json.put("accelerometer", accelerometerData);
            json.put("gyroscope", gyroscopeData);
            return json;
        } catch (org.json.JSONException e) {
            Log.e("SensorData", "Error creating JSON object", e);
            return null;
        }
    }
}
