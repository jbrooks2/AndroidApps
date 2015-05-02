package com.example.hung.hw1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView display;
    private Button button_delete,button_one,button_two,button_three,button_four,button_five,
            button_six,button_seven,button_eight,button_nine,button_zero,button_add,
            button_subtract,button_multiply,button_divide,button_decimal,button_equals, button_adv;
    private Double result;
    private String display_string, string2, operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);
        button_delete = (Button) findViewById(R.id.button_delete);
        button_one = (Button) findViewById(R.id.button_one);
        button_two = (Button) findViewById(R.id.button_two);
        button_three = (Button) findViewById(R.id.button_three);
        button_four = (Button) findViewById(R.id.button_four);
        button_five = (Button) findViewById(R.id.button_five);
        button_six = (Button) findViewById(R.id.button_six);
        button_seven = (Button) findViewById(R.id.button_seven);
        button_eight = (Button) findViewById(R.id.button_eight);
        button_nine = (Button) findViewById(R.id.button_nine);
        button_zero = (Button) findViewById(R.id.button_zero);
        button_add = (Button) findViewById(R.id.button_add);
        button_subtract = (Button) findViewById(R.id.button_subtract);
        button_multiply = (Button) findViewById(R.id.button_multiply);
        button_divide = (Button) findViewById(R.id.button_divide);
        button_decimal = (Button) findViewById(R.id.button_decimal);
        button_equals = (Button) findViewById(R.id.button_equals);

        // Tests if any data is transferred from previous activities
        Intent intent = getIntent();
        display_string = intent.getStringExtra("display");
        string2 = intent.getStringExtra("string2");
        operator = intent.getStringExtra("operator");
        if (display_string == null)
            display_string = "";
        if (string2 == null)
            string2 = "";
        if (operator == null)
            operator = "";
        display.setText(display_string);

        createClicks();

        button_adv = (Button) findViewById(R.id.button_adv);
        button_adv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SciActivity.class);
                intent.putExtra("display", display_string);
                intent.putExtra("string2", string2);
                intent.putExtra("operator", operator);
                // prevents new stacks of activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }

    public void createClicks(){
        setOnClick(button_delete, "delete");
        setOnClick(button_one, "one");
        setOnClick(button_two, "two");
        setOnClick(button_three, "three");
        setOnClick(button_four, "four");
        setOnClick(button_five, "five");
        setOnClick(button_six, "six");
        setOnClick(button_seven, "seven");
        setOnClick(button_eight, "eight");
        setOnClick(button_nine, "nine");
        setOnClick(button_zero, "zero");
        setOnClick(button_add, "add");
        setOnClick(button_subtract, "subtract");
        setOnClick(button_multiply, "multiply");
        setOnClick(button_divide, "divide");
        setOnClick(button_decimal, "decimal");
        setOnClick(button_equals, "equals");
    }

    public void setOnClick(Button button, final String s){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (s){
                    case "zero":
                        display_string += 0;
                        break;
                    case "one":
                        display_string += 1;
                        break;
                    case "two":
                        display_string += 2;
                        break;
                    case "three":
                        display_string += 3;
                        break;
                    case "four":
                        display_string += 4;
                        break;
                    case "five":
                        display_string += 5;
                        break;
                    case "six":
                        display_string += 6;
                        break;
                    case "seven":
                        display_string += 7;
                        break;
                    case "eight":
                        display_string += 8;
                        break;
                    case "nine":
                        display_string += 9;
                        break;
                    case "decimal":
                        if (display_string.length() == 0)
                            display_string += "0.";
                        else {
                            if (!display_string.contains("."))
                                display_string += ".";
                        }
                        break;
                    case "delete":
                        if (!display_string.isEmpty()) {
                            String tempString = display_string.substring(0, display_string.length() - 1);
                            display_string = tempString;
                        }
                        else{
                            string2 = "";
                            display_string = "";
                        }
                        break;
                    case "add":
                        if (string2.isEmpty()) {
                            string2 = display_string;
                            operator = "+";
                            display_string = "";
                        }
                        else {
                            if (display_string.isEmpty()) {
                                calculate();
                                string2 = result + "";
                                display_string = "";
                                operator = "+";
                                display_string = result + "";
                            }
                        }
                        break;
                    case "subtract":
                        if (string2.isEmpty()) {
                            string2 = display_string;
                            operator = "-";
                            display_string = "";
                        }
                        else {
                            if (display_string.isEmpty()) {
                                calculate();
                                string2 = result + "";
                                display_string = "";
                                operator = "-";
                                display_string = result + "";
                            }
                        }
                        break;
                    case "multiply":
                        if (string2.isEmpty()) {
                            string2 = display_string;
                            operator = "*";
                            display_string = "";
                        }
                        else {
                            if (!display_string.isEmpty()) {
                                calculate();
                                string2 = result + "";
                                display_string = "";
                                operator = "*";
                                display_string = result + "";
                            }
                        }
                        break;
                    case "divide":
                        if (string2.isEmpty()) {
                            string2 = display_string;
                            operator = "/";
                            display_string = "";
                        }
                        else {
                            if (!display_string.isEmpty()) {
                                calculate();
                                string2 = result + "";
                                display_string = "";
                                operator = "/";
                                display_string = result + "";
                            }
                        }
                        break;
                    case "equals":
                        if (!operator.isEmpty()) {
                            calculate();
                            string2 = "";
                            display_string = result + "";
                        }
                        break;
                }
                display.setText(display_string);
            }
        });

    }

    public void calculate() {
        double string2Num = Double.parseDouble(string2);
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
                if (display_string.isEmpty())
                    result = Math.E;
                else
                    result = Math.exp(displayNum);
                break;
            case "percent":
                result = displayNum * 0.01;
                break;
            case "root":
                result = Math.sqrt(displayNum);
                break;
            case "factorial":
                try {
                    int tempInt = Integer.parseInt(display_string);
                    result = tempInt + 0.0;
                    while (tempInt > 0) {
                        result *= result * tempInt - 1;
                        tempInt--;
                    }
                } catch (Exception e) {
                    display.setText("Error! Not an integer");
                    display_string = "";
                    string2 = "";
                    operator = "";
                }
                break;
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
