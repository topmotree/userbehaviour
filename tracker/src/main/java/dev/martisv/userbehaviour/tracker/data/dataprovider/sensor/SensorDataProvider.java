package dev.martisv.userbehaviour.tracker.data.dataprovider.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorDataProvider implements SensorEventListener {
    private static SensorManager instance;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private SensorData currentData;

    public SensorDataProvider(Context context) {
        SensorManager sensorManager = (android.hardware.SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public SensorData getSensorData() {
        return currentData;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (currentData == null) {
            currentData = new SensorData();
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            currentData.setAccelerometerData(event.values);
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            currentData.setGyroscopeData(event.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("SensorDataProvider", "onAccuracyChanged: " + sensor.getName() + " " + accuracy);
    }
}
