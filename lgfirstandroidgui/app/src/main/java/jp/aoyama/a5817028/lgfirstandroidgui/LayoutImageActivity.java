package jp.aoyama.a5817028.lgfirstandroidgui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image);

        ImageButton play_btn = (ImageButton)findViewById(R.id.play_btn);
        ImageButton stop_btn = (ImageButton)findViewById(R.id.stop_btn);
       //자바로 액션바 바꾸기
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x33808080));

    }


    public void myBtnClick(View v){
        if (v.getId()==R.id.play_btn){
            TextView tv = (TextView)findViewById(R.id.textView2);
            tv.setText(getResources().getString(R.string.play));
        }
        else if (v.getId()==R.id.stop_btn){
            TextView tv = (TextView)findViewById(R.id.textView2);
            tv.setText(getResources().getString(R.string.stop));
        }
    }
}
