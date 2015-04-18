package tazzie.example.com.pigs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import tazzie.example.com.pigs.R;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll_button, hold;
    private TextView player1, player2, round;
    private int tempScore1, roundNum, score1, score2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (TextView) findViewById(R.id.p1);
        player2 = (TextView) findViewById(R.id.p2);
        round = (TextView) findViewById(R.id.round);
        try{
            Intent intent = getIntent();
            score1 = intent.getIntExtra("score1", 0);
            score2 = intent.getIntExtra("score2", 0);
            roundNum = intent.getIntExtra("roundNum", 0);
        }
        catch(Exception e){
            score1 = 0;
            score2 = 0;
            roundNum = 1;
        }
        tempScore1 = 0;
        Toast.makeText(this, "Player2 score is: " + score2, Toast.LENGTH_LONG).show();

        player1.setText("P1: " + score1);
        player2.setText("P2: " + score2);
        round.setText("Round: " + roundNum);

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
                score1 += tempScore1;
                if (score1 > 99) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("You Won!");
                    alertDialog.setMessage("Cowabunga!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    nextPlayerTurn();
                }
            }
        });
    }

    // generates 2 random integer between 1 and 6 inclusive
    public void rollDice(){
        int val1 = 1 + (int)(6 * Math.random());
        int val2 = 1 + (int)(6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
        setScore(val1, val2);
    }

    //updates scores
    // Ends turn if a die is value 1
    public void setScore(int val1, int val2){
        if (val1 == 1 || val2 == 1) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Rolled 1");
            alertDialog.setMessage("DOH!! You rolled 1. Score rolled back to: " + score1);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            nextPlayerTurn();
                        }
                    });
            alertDialog.show();
        }
        else {
            tempScore1 += val1 + val2;
            player1.setText("P1: " + (score1 + tempScore1));
        }
    }

    public void nextPlayerTurn(){
        Intent intent = new Intent(MainActivity.this, Player2.class);
        intent.putExtra("score1", score1);
        intent.putExtra("score2", score2);
        intent.putExtra("roundNum", roundNum);
        // prevents new stacks of activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
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
