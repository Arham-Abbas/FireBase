package com.arham.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    BluetoothAdapter bluetoothAdapter;
    CameraManager cameraManager;
    Vibrator vibrator;
    boolean torch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        button1 = (Button) findViewById(R.id.button5);
        button2 = (Button) findViewById(R.id.button6);
        button3 = (Button) findViewById(R.id.button7);
        button4 = (Button) findViewById(R.id.button8);
        button5 = (Button) findViewById(R.id.button9);
        button6 = (Button) findViewById(R.id.button10);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        firebaseAuth = FirebaseAuth.getInstance();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signOut();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Logout.this, "Logged out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Logout.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(Logout.this, Manifest.permission.BLUETOOTH_CONNECT)== PackageManager.PERMISSION_GRANTED) {
                    if(bluetoothAdapter.isEnabled())
                        bluetoothAdapter.disable();
                    else
                        bluetoothAdapter.enable();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cameraManager.setTorchMode(cameraManager.getCameraIdList()[0], !torch);
                    torch = !torch;
                }
                catch (CameraAccessException e)
                {
                    Toast.makeText(Logout.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                    e.getSuppressed();
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrationEffect vibrationEffect = VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE);
                vibrator.vibrate(vibrationEffect);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logout.this, Gyro.class);
                startActivity(intent);
                finish();
            }
        });
    }
}