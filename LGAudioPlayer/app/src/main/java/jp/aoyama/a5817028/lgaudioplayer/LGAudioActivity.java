package jp.aoyama.a5817028.lgaudioplayer;


import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;

public class LGAudioActivity extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.kalimba); // media_resouce_name には res フォルダに追加したメディアファイルの名前（拡張子なし）
        mp.start();

        try {
            Thread.sleep(3000);  // Thread.sleep = countdown time
            mp.stop();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lg_audio_menu, menu);
        return true;
    }
}

