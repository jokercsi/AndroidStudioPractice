package jp.aoyama.a5817028.xxinputactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    private EditText name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input);

        ImageButton play_btn = (ImageButton)findViewById(R.id.play_btn);
        ImageButton stop_btn = (ImageButton)findViewById(R.id.stop_btn);

        // android icon in actionbar title
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        else if (v.getId()==R.id.floatingActionButton){
            Intent intent = new Intent(InputActivity.this, SetActivity.class);
            startActivity(intent);
            displayToast(getString(R.string.toast));
            // finish() = turn off App
            //finish();
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
            case R.id.action_settings:
                // TODO : process the click event for action_search item.
                TextView textView0 = (TextView) findViewById(R.id.textView2);
                textView0.append("\n " + getResources().getString(R.string.press_setting));
                return true;
            case R.id.action_edit:
                // TODO : process the click event for action_search item.
                TextView textView1 = (TextView) findViewById(R.id.textView2);
                textView1.append("\n " + getResources().getString(R.string.press_edit));
                return true;
            case R.id.action_open:
                // TODO : process the click event for action_search item.
                TextView textView2 = (TextView) findViewById(R.id.textView2);
                textView2.append("\n " + getResources().getString(R.string.press_open));
                return true;
            case R.id.action_save:
                // TODO : process the click event for action_search item.
                TextView textView3 = (TextView) findViewById(R.id.textView2);
                textView3.append("\n " + getResources().getString(R.string.press_save));

                    //save as txt.file
                    // import present time
                    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd_HHmmss");
                    Date time = new Date();

                    String presentTime = format1.format(time);

                    // set filename
                    String filename = "KJinputactivity_content-" + presentTime + ".txt";
                    File file = new File(getFilesDir(), filename);

                    // set file content
                    String fileContents = textView3.getText().toString();
                    try {
                        FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                        fos.write(fileContents.getBytes());
                        Toast.makeText(this, "saved to " + getFilesDir()+ "/" + filename, Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

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





}