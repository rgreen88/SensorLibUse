package com.example.android.dummy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.android.sensorhelper.SensorHelper;

public class MainActivity extends AppCompatActivity implements SensorCallback{

    private static final String TAG = "value onSensorChange";
    SensorHelper sensorHelper;
    SensorEvent event;
    private Display mDisplay;
    private WindowManager mWindowManager;
    private float mSensorX;
    private float mSensorY;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);
        Log.d(TAG, "onCreate: " + String.valueOf(event)); //null value with non-working interface in lib
        sensorHelper.onSensorChanged(event);

    }

    public void onSensorChanged(SensorEvent event) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
                return;
        }
        Log.d(TAG, "onSensorChanged: ");


        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                textView.setText(String.valueOf(mSensorX));
                Log.d(TAG, "onSensorChanged: "  + String.valueOf(mSensorX)) ;

                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                textView.setText(String.valueOf(mSensorX));
                Log.d(TAG, "onSensorChanged: "  + String.valueOf(mSensorX));

                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                textView.setText(String.valueOf(mSensorX));
                Log.d(TAG, "onSensorChanged: "  + String.valueOf(mSensorX));

                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                textView.setText(String.valueOf(mSensorX));
                Log.d(TAG, "onSensorChanged: " + String.valueOf(mSensorX));
                break;
        }
    }
}
