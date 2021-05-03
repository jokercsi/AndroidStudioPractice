package jp.aoyama.a5817028.lgfirstandroidgui;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton1 = (Button)findViewById(R.id.my_button1);
        Button myButton2 = (Button)findViewById(R.id.my_button2);
    }

    public void myBtnClick(View v){
        if (v.getId()==R.id.my_button1){
            TextView tv = (TextView)findViewById(R.id.textView1);
            tv.setText("I pressed LG 1st Button");
        }
        if (v.getId()==R.id.my_button2){
            TextView tv = (TextView)findViewById(R.id.textView1);
            tv.setText("I pressed LG 2st Button");
        }
    }
}
