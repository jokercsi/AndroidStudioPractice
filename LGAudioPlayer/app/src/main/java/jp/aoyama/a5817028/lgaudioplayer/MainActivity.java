package jp.aoyama.a5817028.lgaudioplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    static final String TAG = "MediaRecording";
    MediaPlayer mp;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lg_audio_menu, menu);
        return true;
    }

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED;
    }
    // Checks if a volume containing external storage is available to at least read.
    private boolean isExternalStorageReadable() {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ||
                Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED_READ_ONLY;
    }

    public void myBtnClick(View v){

        TextView textView0 = (TextView) findViewById(R.id.textInImage);

        long min, sec;

        if (v.getId()==R.id.play_btn){
            mp = MediaPlayer.create(this, R.raw.kalimba);
            mp.start();
            // 노래 전체 시간 가져오기
            int msDur = mp.getDuration();

            min = (msDur / 1000) / 60 % 60;
            sec = (msDur / 1000) % 60;

            textView0.append("\n "+getResources().getString(R.string.start)+ " " + min + " : " + sec);

        }
        else if (v.getId()==R.id.stop_btn){
//            if(mp.isPlaying()== true){
//
//                mp.stop();
//                // 노래의 현재시간 가져오기
//                int msCur = mp.getCurrentPosition();
//
//                min = (msCur / 1000) / 60 % 60;
//                sec = (msCur / 1000) % 60;
//
//                mp.reset();
//                textView0.append("\n "+getResources().getString(R.string.stop)+ " "  + min + " : " + sec );
//            }
            if (null != recorder) {
                try{
                    recorder.stop();
                    recorder.reset();
                    recorder.release();
                }catch (RuntimeException e){
                    Toast.makeText(MainActivity.this,
                            e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this,
                        "IDK", Toast.LENGTH_LONG).show();
            }
        }
        else if (v.getId()==R.id.pause_btn){
            mp.pause();
            int msCur = mp.getCurrentPosition();

            min = (msCur / 1000) / 60 % 60;
            sec = (msCur / 1000) % 60;

            textView0.append("\n "+getResources().getString(R.string.pause)+ " "  + min + " : " + sec);
        }

        else if (v.getId()==R.id.rec_btn){
            //録音準備＆録音開始
            try {
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                //保存先
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp";
                Toast.makeText(MainActivity.this,
                        path, Toast.LENGTH_LONG).show();

                recorder.setOutputFile(path);
                recorder.prepare();
                recorder.start();   //録音開始

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this,
                        e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}