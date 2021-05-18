package jp.aoyama.a5817028.lgaudioplayer;

import androidx.appcompat.app.AppCompatActivity;

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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lg_audio_menu, menu);
        return true;
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
            if(mp.isPlaying()== true){
                mp.stop();
                // 노래의 현재시간 가져오기
                int msCur = mp.getCurrentPosition();

                min = (msCur / 1000) / 60 % 60;
                sec = (msCur / 1000) % 60;

                mp.reset();
                textView0.append("\n "+getResources().getString(R.string.stop)+ " "  + min + " : " + sec );
            }
            else if (null != recorder) {
                try{
                    recorder.stop();
                    recorder.reset();
                    recorder.release();
                }catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(MainActivity.this,
                        "Your Message", Toast.LENGTH_LONG).show();
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

            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //保存先
            String filePath = Environment.getExternalStorageDirectory() + "/audio.3gp";
            recorder.setOutputFile(filePath);

            //録音準備＆録音開始
            try {
                recorder.prepare();
                recorder.start();   //録音開始
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}