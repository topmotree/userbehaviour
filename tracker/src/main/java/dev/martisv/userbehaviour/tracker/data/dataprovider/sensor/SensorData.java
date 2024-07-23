package dev.martisv.userbehaviour.tracker.data.dataprovider.sensor;

import android.util.Log;

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

    public float getAccelerometerX() {
        return accelerometerX;
    }

    public float getAccelerometerY() {
        return accelerometerY;
    }

    public float getAccelerometerZ() {
        return accelerometerZ;
    }

    public float getGyroscopeX() {
        return gyroscopeX;
    }

    public float getGyroscopeY() {
        return gyroscopeY;
    }

    public float getGyroscopeZ() {
        return gyroscopeZ;
    }
}
