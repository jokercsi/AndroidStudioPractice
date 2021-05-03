package jp.aoyama.a5817028.lifecycleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = " LifeCycleActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart()");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume()");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause()");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop()");
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}