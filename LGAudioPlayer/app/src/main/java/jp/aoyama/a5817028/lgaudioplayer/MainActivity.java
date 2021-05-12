package jp.aoyama.a5817028.lgaudioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            Thread.sleep(3000);  // Thread.sleep = countdown time
//            mp.stop();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lg_audio_menu, menu);
        return true;
    }

    public void myBtnClick(View v){

        TextView textView0 = (TextView) findViewById(R.id.textInImage);

        if (v.getId()==R.id.play_btn){
            mp = MediaPlayer.create(this, R.raw.kalimba);
            mp.start();

            textView0.append("\n "+getResources().getString(R.string.start));

        }
        else if (v.getId()==R.id.stop_btn){
            mp.stop();
            mp.reset();

            textView0.append("\n "+getResources().getString(R.string.stop));
        }
        else if (v.getId()==R.id.pause_btn){
            mp.pause();

            textView0.append("\n "+getResources().getString(R.string.pause));

        }
        else if (v.getId()==R.id.rec_btn){

        }
    }
}