package com.detroitteatime.uidemo2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

@SuppressWarnings("deprications")
public class MainActivity extends Activity implements ActionBar.TabListener{
    public static final String TAG = "MY TAG";
    private String message;
    private Button button;

    ActionBar.Tab firstL, secondL;
    Fragment firstList;
    Fragment secondList;

    // Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {


            message = savedInstanceState.getString("message");
        }

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(
                actionBar.newTab()
                        .setText("First List")
                        .setTabListener(this));

        actionBar.addTab(
                actionBar.newTab()
                        .setText("Second List")
                        .setTabListener(this));



        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "Howdy!";
                Log.i(TAG, "message set to 'Howdy!'");
            }
        });



        Log.i(TAG, "Activity onCreate method called");
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "Activity onStart method called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "Activity onResume method called");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "Activity onPause method called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "Activity onStop method called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Activity onDestroy method called");
    }


    // Other callbacks

    @Override

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", "Howdy Doody!");
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction FragTrans) {
        firstList = new DemoFragment();
        secondList = new DemoFragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        if(tab.getPosition()==0){

            ft.replace(R.id.container, firstList);
            firstList.setRetainInstance(true);
            ft.commit();
        }else{
            ft.replace(R.id.container, secondList);
            secondList.setRetainInstance(true);
            ft.commit();
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
