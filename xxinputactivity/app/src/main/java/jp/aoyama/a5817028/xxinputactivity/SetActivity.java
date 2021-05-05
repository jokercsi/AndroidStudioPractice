package jp.aoyama.a5817028.xxinputactivity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // save SharedPref
    private EditText EditName, EditId, EditEmail, EditPhone;
    private TextView textView;
    private FloatingActionButton fab;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String Name = "name";
    public static final String Email = "email";
    public static final String Id = "id";
    public static final String Phone = "number";

    private String nameSt;
    private String emailSt;
    private String idSt;
    private int phoneSt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        // show Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        // Saving data with shared preferences
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonSave);
        EditName = (EditText) findViewById(R.id.name);
        EditEmail  = (EditText) findViewById(R.id.email);
        EditId = (EditText) findViewById(R.id.ID);
        EditPhone = (EditText) findViewById(R.id.phone);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                saveTxtFile();
            }
        });
        loadData();
        updateData();


    }

    public void saveData(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Save Data => editor.putString(key, value)
        editor.putString(Name, EditName.getText().toString());
        editor.putString(Email, EditEmail.getText().toString());
        editor.putString(Id, EditId.getText().toString());
        editor.putInt(Phone, Integer.parseInt(EditPhone.getText().toString()));
        editor.commit();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        nameSt = sharedPreferences.getString(Name, " ");
        emailSt = sharedPreferences.getString(Email, " ");
        idSt = sharedPreferences.getString(Id, " ");
        phoneSt = sharedPreferences.getInt(Phone, 0);
    }

    // update data in EditText
    public void updateData(){
        EditName.setText(nameSt);
        EditEmail.setText(emailSt);
        EditId.setText(idSt);
        EditPhone.setText(String.valueOf(phoneSt));
    }


    public void saveTxtFile(){
        // import present time
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd_HHmmss");
        Date time = new Date();

        String presentTime = format1.format(time);

        // set filename
        String filename = "KJsetactivity_pref-" + presentTime + ".txt";
        File file = new File(getFilesDir(), filename);

        // set file content
        loadData();
        String fileContents = "Bundle[{id="+ idSt + ", mail"+ emailSt + ", user=" +nameSt+", phone="  + phoneSt + "}]";
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(fileContents.getBytes());
            Toast.makeText(this, "saved to " + getFilesDir()+ "/" + filename, Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        /** ここで必要なコードを書く **/

        // Check which radio button was clicked.

        /** ここで必要なコードを書く **/

    }

    @Override
    public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        /** Toast 表示コードを追加する **/
        Toast.makeText(getApplicationContext(), spinnerLabel, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView adapterView) { }
    // hi
}