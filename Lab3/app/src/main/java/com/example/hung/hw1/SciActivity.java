package com.example.hung.hw1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class SciActivity extends ActionBarActivity {
    private TextView display;
    private Button button_delete, button_basic, button_sin, button_cos, button_tan, button_i,
            button_ln, button_log, button_pi, button_e, button_percent,
            button_factorial, button_root, button_power, button_leftParen, button_rightParen;
    private Double result;
    private String display_string, string2, operator;
    private Boolean is_string_pi, is_string_e, is_power_on;

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
        Intent intent = getIntent();
        display_string = intent.getStringExtra("display");
        string2 = intent.getStringExtra("string2");
        operator = intent.getStringExtra("operator");
        try {
            is_string_pi = intent.getBooleanExtra("is_string_pi", is_string_pi);
            is_string_e = intent.getBooleanExtra("is_string_e", is_string_e);
            is_power_on = intent.getBooleanExtra("is_power_on", is_power_on);
        }
        catch (NullPointerException e){
            is_power_on = false;
            is_string_e = false;
            is_string_pi = false;
        }
        if (display_string == null)
            display_string = "";
        if (string2 == null)
            string2 = "";
        if (operator == null)
            operator = "";
        display.setText(display_string);

        button_basic = (Button) findViewById(R.id.button_basic);
        button_basic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SciActivity.this,MainActivity.class);
                intent.putExtra("display", display_string);
                intent.putExtra("string2", string2);
                intent.putExtra("operator", operator);
                intent.putExtra("is_string_pi", is_string_pi);
                intent.putExtra("is_string_e", is_string_e);
                intent.putExtra("is_power_on", is_power_on);
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
        setOnClick(button_power, "power");
        setOnClick(button_leftParen, "leftParen");
        setOnClick(button_rightParen, "rightParen");
    }

    public void setOnClick(Button button, final String s){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (s){
                    case "delete":
                        if (display_string.isEmpty() || is_string_pi || is_string_e) {
                            string2 = "";
                            display_string = "";
                        }
                        else{
                            String tempString = display_string.substring(0, display_string.length() - 1);
                            display_string = tempString;
                        }
                        break;
                    case "sin":
                        operator = "sin";
                        calculate();
                        display_string = result + "";
                        break;
                    case "cos":
                        operator = "cos";
                        calculate();
                        display_string = result + "";
                        break;
                    case "tan":
                        operator = "tan";
                        calculate();
                        display_string = result + "";
                        break;
                    case "ln":
                        operator = "ln";
                        calculate();
                        display_string = result + "";
                        break;
                    case "log":
                        operator = "log";
                        calculate();
                        display_string = result + "";
                        break;
                    case "pi":
                        is_string_pi = true;
                        display_string = Math.PI + "";
                        break;
                    case "e":
                        if (display_string.isEmpty()) {
                            is_string_e = true;
                            display_string = Math.E + "";
                        }
                        else {
                            operator = "e";
                            calculate();
                            display_string = result + "";
                        }
                        break;
                    case "percent":
                        operator = "percent";
                        calculate();
                        display_string = result + "";
                        break;
                    case "factorial":
                        operator = "factorial";
                        calculate();
                        display_string = result + "";
                        break;
                    case "root":
                        operator = "root";
                        calculate();
                        display_string = result + "";
                        break;
                    case "power":
                        is_power_on = true;
                        operator = "^";
                        string2 = display_string;
                        display_string = "";
                        break;
//                    case "leftParen":
//                        display_string += "(";
//                        break;
//                    case "rightParen":
//                        display_string += ")";
//                        break;
                }
                display.setText(display_string);
            }
        });
    }

    public void calculate() {
        double string2Num = 0.0;
        if (!string2.isEmpty())
            string2Num = Double.parseDouble(string2);
        double displayNum = Double.parseDouble(display_string);
        switch (operator) {
            case "+":
                result = string2Num + displayNum;
                break;
            case "-":
                result = string2Num - displayNum;
                break;
            case "/":
                result = string2Num / displayNum;
                break;
            case "*":
                result = string2Num * displayNum;
                break;
            case "sin":
                result = Math.sin(displayNum);
                break;
            case "cos":
                result = Math.cos(displayNum);
                break;
            case "tan":
                result = Math.tan(displayNum);
                break;
            case "ln":
                result = Math.log(displayNum);
                break;
            case "log":
                result = Math.log10(displayNum);
                break;
            case "e":
                result = Math.exp(displayNum);
                break;
            case "percent":
                result = displayNum * 0.01;
                break;
            case "root":
                result = Math.sqrt(displayNum);
                break;
            case "factorial":
                getFactorial();
                break;
        }
        operator = "";
    }

    public void getFactorial(){
        int tempInt = Integer.parseInt(display_string);
        result = tempInt + 0.0;
        tempInt--;
        while (tempInt > 0) {
            result *= tempInt;
            tempInt--;
        }
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
