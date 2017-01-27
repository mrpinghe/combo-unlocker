package com.mrpinghe.android.combofinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";

    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance() {
        return new MainActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        view.findViewById(R.id.btn_nxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(v);
            }
        });

        return view;
    }


    private void calculate(View v) {
        TextView err = (TextView) v.getRootView().findViewById(R.id.step_1_error);

        EditText firstLp = (EditText) v.getRootView().findViewById(R.id.first_lp);
        EditText secondLp = (EditText) v.getRootView().findViewById(R.id.second_lp);
        EditText resisLoc = (EditText) v.getRootView().findViewById(R.id.resis_loc);

        try {
            Integer firstP = Integer.parseInt(firstLp.getText().toString());
            Integer secondP = Integer.parseInt(secondLp.getText().toString());
            Double resisL = Double.parseDouble(resisLoc.getText().toString());

            StringBuffer error = new StringBuffer();

            if (firstP <= 0 || firstP >= 11) {
                error.append(" - First Locked Position should be integer between 0 and 11\n");
            }
            if (secondP <= 0 || secondP >= 11) {
                error.append(" - Second Locked Position should be integer between 0 and 11\n");
            }
            if (resisL <= 0.0 || resisL >= 40.0) {
                error.append(" - Resistant Position is out of range");
            }
            else {
                String[] dub = resisL.toString().split("\\.");
                if (dub.length > 1 && !dub[1].equals("0") && !dub[1].equals("5")) {
                    error.append(" - Resistant Position should be whole or half number");
                }
            }

            if (error.length() > 0) {
                err.setText(error.toString());
            }
            else {
                ((MainActivity) this.getActivity()).goToSecondStep(firstP, secondP, resisL);
            }


        } catch (NumberFormatException nfe) {

        }
    }
}
