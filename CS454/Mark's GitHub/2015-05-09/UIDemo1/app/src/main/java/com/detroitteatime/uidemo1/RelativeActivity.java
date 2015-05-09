package com.detroitteatime.uidemo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by mark on 4/16/15.
 */
public class RelativeActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_relative_layout);

        final  EditText name, password, email, number;

        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        number = (EditText)findViewById(R.id.number);

        final RadioGroup gender = (RadioGroup)findViewById(R.id.gender);




        //set up spinner with adapter
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RelativeActivity.this, spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ToggleButton tb = (ToggleButton)findViewById(R.id.toggleButton);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tb.isChecked()){
                    tb.setText("On the job");
                }
                else{
                    tb.setText("Not on the job");
                }
            }
        });


        Button toGrid, enter;

        toGrid = (Button)findViewById(R.id.toGrid);
        toGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RelativeActivity.this, GridActivity.class);
                startActivity(intent);
            }
        });

        enter = (Button)findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameS = name.getText().toString();
                String passwordS = password.getText().toString();
                String emailS = email.getText().toString();
                String numberS = number.getText().toString();


                RadioButton choice = (RadioButton)findViewById(gender.getCheckedRadioButtonId());
                String genderS = choice.getText().toString();





                String toastString = String.format("Name: %s, Password: %s, Email: %s, Number: %s, Gender %s", nameS, passwordS, emailS, numberS, genderS);
                Toast.makeText(RelativeActivity.this,toastString , Toast.LENGTH_LONG).show();
            }
        });


    }




}
