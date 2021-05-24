package jp.aoyama.a5817028.lgsensormonitor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class LGSensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView xAxis,yAxis,zAxis,
             sensorInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorManager sma = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sma.registerListener(this, sma.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

        setContentView(R.layout.lg_sensor_layout_main);


        xAxis = (TextView) findViewById(R.id.xAxis);
        yAxis = (TextView) findViewById(R.id.yAxis);
        zAxis = (TextView) findViewById(R.id.zAxis);
        sensorInformation = (TextView) findViewById(R.id.accelerator);

    }

    // 중력가속도
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void onSensorChanged(SensorEvent event){
        double x = event.values[0];
        double y = event.values[1];
        double z = event.values[2];

        xAxis.setText("X = " + x);
        yAxis.setText("  Y = " + y);
        zAxis.setText("  Z = " + z);

        long start = System.currentTimeMillis(); // start to countdown

        //이름
        String name = event.sensor.getName();
        sensorInformation.setText("Name: "+ name);
        //종류
        String type = event.sensor.getStringType();
        sensorInformation.append("\nType: " + type);
        //vendor
        String vendor = event.sensor.getVendor();
        sensorInformation.append("\nVendor: " + vendor);
        //version
        int version = event.sensor.getVersion();
        sensorInformation.append("\nVersion: " + version);
        //resolution
        double resolution = event.sensor.getResolution();
        sensorInformation.append("\nResolution: " + resolution);
        //max Range
        double maxRange = event.sensor.getMaximumRange();
        sensorInformation.append("\nMax range: " + maxRange);

        double power = event.sensor.getPower();
        sensorInformation.append("\nPower: " + power +"mA");

        //samples (samples) = Duration (sec) / Sampling Interval (sec)/ number of processors


        long end = System.currentTimeMillis(); // end to countdown
        sensorInformation.append("\nSampling interval: " + (end - start) + "msec");




    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}