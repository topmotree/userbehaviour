package dev.martisv.userbehaviour.tracker.data.saver.dto;

import org.json.JSONException;
import org.json.JSONObject;

import dev.martisv.userbehaviour.tracker.data.dataprovider.sensor.SensorData;

public class SensorDataDto {
    private float accelerometerX, accelerometerY, accelerometerZ;
    private float gyroscopeX, gyroscopeY, gyroscopeZ;

    private SensorDataDto(float accelerometerX, float accelerometerY, float accelerometerZ, float gyroscopeX, float gyroscopeY, float gyroscopeZ) {
        this.accelerometerX = accelerometerX;
        this.accelerometerY = accelerometerY;
        this.accelerometerZ = accelerometerZ;
        this.gyroscopeX = gyroscopeX;
        this.gyroscopeY = gyroscopeY;
        this.gyroscopeZ = gyroscopeZ;
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

    public static SensorDataDto fromSensorData(SensorData sensorData) {
        return new SensorDataDto(
                sensorData.getAccelerometerX(), sensorData.getAccelerometerY(), sensorData.getAccelerometerZ(),
                sensorData.getGyroscopeX(), sensorData.getGyroscopeY(), sensorData.getGyroscopeZ()
        );
    }
}
