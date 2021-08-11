package jp.aoyama.a5817028.lgsensormonitor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LGSensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView  sensorInformation;
    double x, y ,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorManager sma = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sma.registerListener(this, sma.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

        setContentView(R.layout.lg_sensor_layout_main);

        sensorInformation = (TextView) findViewById(R.id.accelerator);

        final Button button = findViewById(R.id.button_id);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // set filename
                String filename = "KJsensorActivity_content.txt";
                File file = new File(getFilesDir(), filename);

                //시간 차로 textview에 append 하기
                new Handler().postDelayed(new Runnable() {
                    //count number = t
                    int t = 0;
                    @Override
                    public void run() {
                        //count time in loop


                        // 시간
                        long now = System.currentTimeMillis();
                        Date mDate = new Date(now);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                        String getTime = simpleDate.format(mDate);

                        sensorInformation.append(t*5 + " seconds \n" + getTime + "\n X = " + x + "\n Y = " + y + "\n Z = " + z + "\n\n");

                        // set file content
                        String fileContents = sensorInformation.getText().toString();
                        try {
                            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                            fos.write(fileContents.getBytes());
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }

                        if(t<4) {
                            new Handler().postDelayed(this, 5000);
                        }
                        t++;
                    }
                }, 0);
            }
        });



    }



    // 중력가속도 (계속 바뀜..... 계속 갱신됌.) -> timestamp 사용하기.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void onSensorChanged(SensorEvent event){
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}