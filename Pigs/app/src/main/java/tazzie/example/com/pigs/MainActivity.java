package tazzie.example.com.pigs;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import tazzie.example.com.pigs.R;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll_button, hold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);
        roll_button = (Button) findViewById(R.id.roll_button);
        roll_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
        hold = (Button) findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Player2.class);
                intent.putExtra("score", 99);
                // prevents new stacks of activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    // generates 2 random integer between 1 and 6 inclusive
    public void rollDice(){
        int val1 = 1 + (int)(6 * Math.random());
        int val2 = 1 + (int)(6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
    }

    // set appropriate picture to die
    public void setDie(int value, FrameLayout layout){
        Drawable pic = null;
        switch (value){
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
             break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
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
