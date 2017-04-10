package hu.petrik.isit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String name = null;
    String date = null;
    String gender = null;

    String date_now = null;

    EditText et_date = null;
    EditText et_name = null;
    RadioGroup rg = null;
    RadioButton male = null;
    RadioButton female = null;

    ImageButton xmas = null;
    Button bday = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        xmas = (ImageButton)findViewById(R.id.xmasButton);
        xmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = null;

                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Is it Christmas?");
                if(date_now.equals("12.24") || date_now.equals("12.25") ||
                        date_now.equals("12.26")) {
                    result = "Yes!!";
                }else{
                    result = "No :(";
                }
                alertDialogBuilder
                        .setMessage(result)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        bday = (Button)findViewById(R.id.bdayButton);
        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date == null || name == null || gender == null) {
                    Toast.makeText(MainActivity.this, R.string.emptyError, Toast.LENGTH_LONG).show();
                } else if (et_name.getText().toString() == "" || et_date.getText().toString() == "") {
                    Toast.makeText(MainActivity.this, R.string.emptyError, Toast.LENGTH_LONG).show();
                } else {
                    String result = null;

                    AlertDialog.Builder alertDialogBuilder = new
                            AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Is it my birthday?");
                    if(date_now.equals(date)) {
                        result = "Yes!! Happy birthday " + name + "!";
                    }else{
                        result = "No, I'm sorry " + name + " :(";
                    }
                    alertDialogBuilder
                            .setMessage(result)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        et_name = (EditText)findViewById(R.id.nameField);
        et_date = (EditText)findViewById(R.id.dobField);

        rg = (RadioGroup)findViewById(R.id.genderRadioGroup);
        male = (RadioButton)findViewById(R.id.radioMale);
        female = (RadioButton)findViewById(R.id.radioFemale);

        java.text.DateFormat df = new SimpleDateFormat("MM.dd");
        date_now = df.format(Calendar.getInstance().getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            String infoText = "Name: " + name + " DOB: " + date + " Gender: " + gender;

            if(date == null || name == null || gender == null) {
                Toast.makeText(MainActivity.this, R.string.emptyError, Toast.LENGTH_LONG).show();
            } else if (et_name.getText().toString() == "" || et_date.getText().toString() == "") {
                Toast.makeText(MainActivity.this, R.string.emptyError, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, infoText, Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveData(View v){
        name = et_name.getText().toString();
        date = et_date.getText().toString();

        if(rg.getCheckedRadioButtonId() == male.getId()) {
            gender = "male";
        } else {
            gender = "female";
        }
    }

}
