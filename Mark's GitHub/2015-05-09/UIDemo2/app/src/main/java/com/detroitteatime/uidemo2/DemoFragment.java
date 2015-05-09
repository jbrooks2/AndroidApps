package com.detroitteatime.uidemo2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by mark on 4/23/15.
 */
public class DemoFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(MainActivity.TAG, "Fragment onAttach called");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MainActivity.TAG, "Fragment onCreatecalled");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final String[] values = new String[] { "Mermelade", "Cranelourus", "Termilon", "Dicept", "Forminal",
        "Tortous", "Hylomorphism", "Opicity", "Storkture", "Mermelade", "Cranelourus", "Termilon", "Dicept", "Forminal",
                "Tortous", "Hylomorphism", "Opicity", "Storkture", "Mermelade", "Cranelourus", "Termilon", "Dicept", "Forminal",
                "Tortous", "Hylomorphism", "Opicity", "Storkture"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        ListView list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View frenchfry, int i, long l) {
                Toast.makeText(getActivity(), values[i], Toast.LENGTH_LONG).show();
                //frenchfry.setBackground(getActivity().getResources().getDrawable(R.drawable.die_face_5));
            }
        });



        Log.i(MainActivity.TAG, "Fragment onCreateView called");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(MainActivity.TAG, "Fragment onActivityCreated called");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        Log.i(MainActivity.TAG, "Fragment onStart called");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(MainActivity.TAG, "Fragment onResume called");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(MainActivity.TAG, "Fragment onPause called");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(MainActivity.TAG, "Fragment onStop called");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(MainActivity.TAG, "Fragment onDestroyView called");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(MainActivity.TAG, "Fragment onDestroy called");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(MainActivity.TAG, "Fragment onDetach called");
        super.onDetach();
    }
}
