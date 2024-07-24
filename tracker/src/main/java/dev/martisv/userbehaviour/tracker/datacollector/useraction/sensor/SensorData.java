package dev.martisv.userbehaviour.tracker.datacollector.useraction.sensor;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorData {
    private float accelerometerX, accelerometerY, accelerometerZ;
    private float gyroscopeX, gyroscopeY, gyroscopeZ;

    public void setAccelerometerData(float[] data) {
        if (data != null && data.length >= 3) {
            this.accelerometerX = data[0];
            this.accelerometerY = data[1];
            this.accelerometerZ = data[2];
        } else {
            Log.e("SensorData", "Invalid accelerometer data array");
        }
    }

    public void setGyroscopeData(float[] data) {
        if (data != null && data.length >= 3) {
            this.gyroscopeX = data[0];
            this.gyroscopeY = data[1];
            this.gyroscopeZ = data[2];
        } else {
            Log.e("SensorData", "Invalid gyroscope data array");
        }
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        JSONObject accelerometerJson = new JSONObject();
        accelerometerJson.put("x", this.accelerometerX);
        accelerometerJson.put("y", this.accelerometerY);
        accelerometerJson.put("z", this.accelerometerZ);

        JSONObject gyroscopeJson = new JSONObject();
        gyroscopeJson.put("x", this.gyroscopeX);
        gyroscopeJson.put("y", this.gyroscopeY);
        gyroscopeJson.put("z", this.gyroscopeZ);

        json.put("accelerometer", accelerometerJson);
        json.put("gyroscope", gyroscopeJson);
        return json;
    }
}
