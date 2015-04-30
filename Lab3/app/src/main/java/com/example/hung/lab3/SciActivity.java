package com.example.hung.lab3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hung.lab3.R;


public class SciActivity extends ActionBarActivity {
    private TextView display;
    private Button button_delete, button_basic, button_sin, button_cos, button_tan, button_i,
            button_ln, button_log, button_pi, button_e, button_percent,
            button_factorial, button_root, button_power, button_leftParen, button_rightParen;
    private Double result;
    private String display_string, string2, operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scientific);
        display = (TextView) findViewById(R.id.display);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_sin = (Button) findViewById(R.id.button_sin);
        button_cos = (Button) findViewById(R.id.button_cos);
        button_tan = (Button) findViewById(R.id.button_tan);
        button_i = (Button) findViewById(R.id.button_i);
        button_ln = (Button) findViewById(R.id.button_ln);
        button_log = (Button) findViewById(R.id.button_log);
        button_pi = (Button) findViewById(R.id.button_pi);
        button_e = (Button) findViewById(R.id.button_e);
        button_percent = (Button) findViewById(R.id.button_percent);
        button_factorial = (Button) findViewById(R.id.button_factorial);
        button_root = (Button) findViewById(R.id.button_root);
        button_power = (Button) findViewById(R.id.button_power);
        button_leftParen = (Button) findViewById(R.id.button_leftParen);
        button_rightParen = (Button) findViewById(R.id.button_rightParen);

        // Tests if any data is transferred from previous activities
        try{
            Intent intent = getIntent();
            display_string = intent.getStringExtra("display");
            string2 = intent.getStringExtra("string2");
            operator = intent.getStringExtra("operator");
            display.setText(display_string);
        }
        catch(Exception e){
            display_string = "";
            string2 = "";
            operator = "";
        }
        button_basic = (Button) findViewById(R.id.button_basic);
        button_basic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SciActivity.this,MainActivity.class);
                intent.putExtra("display", display_string);
                // prevents new stacks of activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
        createClicks();
    }

    public void createClicks(){
        setOnClick(button_delete, "delete");
        setOnClick(button_sin, "sin");
        setOnClick(button_cos, "cos");
        setOnClick(button_tan, "tan");
        setOnClick(button_i, "i");
        setOnClick(button_ln, "ln");
        setOnClick(button_log, "log");
        setOnClick(button_pi, "pi");
        setOnClick(button_e, "e");
        setOnClick(button_percent, "percent");
        setOnClick(button_factorial, "factorial");
        setOnClick(button_root, "root");
//        setOnClick(button_power, "power");
//        setOnClick(button_leftParen, "leftParen");
//        setOnClick(button_rightParen, "rightParen");
    }

    public void setOnClick(Button button, final String s){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (s){
                    case "delete":
                        if (!display_string.isEmpty()) {
                            String tempString = display_string.substring(0, display_string.length() - 1);
                            display_string = tempString;
                            display.setText(display_string);
                        }
                        else{
                            display_string = "";
                            display.setText(display_string);
                        }
                        break;
                    case "sin":
                        operator = "sin";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "cos":
                        operator = "cos";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "tan":
                        operator = "tan";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "i":
//                        operator = "i";
//                        calculate();
//                        display.setText(result+"");
                        break;
                    case "ln":
                        operator = "ln";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "log":
                        operator = "log";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "pi":
                        display_string = Math.PI + "";
                        display.setText(display_string);
                        break;
                    case "e":
                        operator = "e";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "percent":
                        operator = "percent";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "factorial":
                        operator = "factorial";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "root":
                        operator = "root";
                        calculate();
                        display.setText(result+"");
                        break;
                    case "power":
                        string2 = display_string;
                        display_string = "";
                        display.setText(string2);
                        break;
                    case "leftParen":
                        display_string += "(";
                        display.setText(display_string);
                        break;
                    case "rightParen":
                        display_string += ")";
                        display.setText(display_string);
                        break;
                }
            }
        });

    }

    public void calculate() {
        double value1 = Double.parseDouble(string2);
        double value2 = Double.parseDouble(display_string);
        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "/":
                result = value1 / value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "sin":
                result = Math.sin(value2);
                break;
            case "cos":
                result = Math.cos(value2);
                break;
            case "tan":
                result = Math.tan(value2);
                break;
//            case "i":
//                result = Math.(value2);
//                break;
            case "ln":
                result = Math.log(value2);
                break;
            case "log":
                result = Math.log10(value2);
                break;
            case "e":
                if (display_string.isEmpty())
                    result = Math.E;
                else
                    result = Math.exp(value2);
                break;
            case "percent":
                result = value2 * 0.01;
                break;
            case "root":
                result = Math.sqrt(value2);
                break;
//            case "factorial":
//                try{
//                    int tempInt = Integer.parseInt(display_string);
//
//                }
//                result = Math.sin(value2);
//                break;
        }
        operator = "";
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
