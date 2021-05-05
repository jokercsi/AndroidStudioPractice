package jp.aoyama.a5817028.xxinputactivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    private String txtFile;

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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
                if(txtFile == null){
                    TextView textView1 = (TextView) findViewById(R.id.textView2);
                    textView1.append("\n " + getResources().getString(R.string.press_edit));
                }
                else {
                    try {
                        FileInputStream fis = openFileInput(txtFile);
                        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                        BufferedReader reader = new BufferedReader(inputStreamReader);

                        TextView showFileContent = (TextView) findViewById(R.id.textView2);
                        showFileContent.setText("");

                        String line;
                        while ((line = reader.readLine()) != null) {
                            showFileContent.append("\n " + line);
                        }


                    } catch (IOException e) {
                        // Error occurred when opening raw file for reading.
                        Toast.makeText(getApplicationContext(), txtFile, Toast.LENGTH_SHORT).show();

                    }
                }

                return true;
            case R.id.action_open:
                // TODO : process the click event for action_search item.
                TextView textView2 = (TextView) findViewById(R.id.textView2);
                textView2.append("\n " + getResources().getString(R.string.press_open));

                    // go to open_layout
                    // open_layout화면 보여줌
                    setContentView(R.layout.open_layout);
                    Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();

                    // show Back Button
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    // show files as listView
                    // listView를 보여줌
                    String[] files = fileList();
                    ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_single_choice,
                            files);
                    ListView listView = (ListView) findViewById(R.id.list);
                    listView.setAdapter(adapter);

                    // listview를 누르면 효과 생기게 하기
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView adapter, View view, int i, long l) {
                            //선택된 txt파일 name이라는 string에 저장
                            txtFile = adapter.getItemAtPosition(i).toString();
                            // Layout_input.xml 화면 보여줌
                            setContentView(R.layout.layout_input);
                            //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();

                            // TextView에 선택한 listView 표기하기
                            TextView showFileTitle = (TextView) findViewById(R.id.textView2);
                            showFileTitle.setText("");
                            showFileTitle.append("opend file = " + txtFile);
                        }
                    });

                return true;
            case R.id.action_save:
                // TODO : process the click event for action_search item.
                TextView textView3 = (TextView) findViewById(R.id.textView2);
                textView3.append("\n " + getResources().getString(R.string.press_save));

                    // when pressing the save menu, save it as txt file
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
