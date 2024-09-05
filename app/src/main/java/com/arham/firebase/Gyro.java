package com.arham.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Gyro extends AppCompatActivity implements SensorEventListener {

    TextView textView;
    Button button;
    SensorManager sensorManager;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        textView = (TextView) findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener((SensorEventListener) Gyro.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Gyro.this, Logout.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String string = "X: " + event.values[0] +"\n\nY: " + event.values[1] + "\n\nZ: " + event.values[2];
        textView.setText(string);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}