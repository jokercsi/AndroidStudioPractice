package jp.aoyama.a5817028.lgfirstandroidgui;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image);


        // image button
        ImageButton play_btn = (ImageButton)findViewById(R.id.play_btn);
        ImageButton stop_btn = (ImageButton)findViewById(R.id.stop_btn);

        // android icon in actionbar title
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // back button in actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //showing actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    // click actionbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings :
                // TODO : process the click event for action_search item.
                TextView textView0 = (TextView) findViewById(R.id.textView2);
                textView0.append("\n "+getResources().getString(R.string.press_setting));
                return true ;
            case R.id.action_edit :
                // TODO : process the click event for action_search item.
                TextView textView1 = (TextView) findViewById(R.id.textView2);
                textView1.append("\n "+getResources().getString(R.string.press_edit));
                return true ;
            case R.id.action_preview :
                // TODO : process the click event for action_search item.
                TextView textView2 = (TextView) findViewById(R.id.textView2);
                textView2.append("\n "+getResources().getString(R.string.press_preview));
                return true ;
            case R.id.action_save :
                // TODO : process the click event for action_search item.
                TextView textView3 = (TextView) findViewById(R.id.textView2);
                textView3.append("\n "+getResources().getString(R.string.press_save));
                return true ;

            // back button 눌렀을 때
            case android.R.id.home:
                TextView textView4 = (TextView) findViewById(R.id.textView2);
                textView4.append("\n "+getResources().getString(R.string.press_back));
                return true;

            default :
                return super.onOptionsItemSelected(item) ;
        }
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
