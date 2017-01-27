package com.mrpinghe.android.combofinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ThirdPickerFragment extends Fragment {

    private int mFirstLP;
    private int mSecondLP;
    private double mResistP;
    private int mFirst;
    private int mMod;

    public static ThirdPickerFragment newInstance(int firstLP, int secondLP, double resistP) {
        ThirdPickerFragment fragment = new ThirdPickerFragment();
        fragment.mFirstLP = firstLP;
        fragment.mSecondLP = secondLP;
        fragment.mResistP = resistP;
        return fragment;
    }

    public ThirdPickerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third_picker, container, false);

        mFirst = ((int) (Math.ceil(this.mResistP) + 5)) % 40;
        mMod = mFirst % 4;
        List<Integer> possibleThird = new ArrayList<Integer>();

        for (int i = 0; i < 4; i++) {
            if ((10*i+mFirstLP)%4 == mMod) {
                possibleThird.add(10*i+mFirstLP);
            }
            if ((10*i+mSecondLP)%4 == mMod) {
                possibleThird.add(10*i+mSecondLP);
            }
        }

        Button btn_one = (Button) v.findViewById(R.id.btn_choice_one);
        btn_one.setText(possibleThird.get(0).toString());
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPossibleSeconds(v);
            }
        });

        Button btn_two = (Button) v.findViewById(R.id.btn_choice_two);
        btn_two.setText(possibleThird.get(1).toString());
        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPossibleSeconds(v);
            }
        });

        InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        return v;
    }

    private void getPossibleSeconds(View v) {

        try {
            int third = Integer.parseInt(((Button) v).getText().toString());

            List<Integer> possibleSeconds = new ArrayList<Integer>();

            for (int i = 0; i < 10; i++) {
                int tmp = (mMod+2) % 4 + 4 * i;
                if ((third+2)%40 != tmp && (third-2)%40 != tmp) {
                    possibleSeconds.add(tmp);
                }
            }

            TextView finalResult = (TextView) v.getRootView().findViewById(R.id.final_result);
            StringBuffer result = new StringBuffer();
            result.append(" => First: ").append(mFirst).append(" (turn clock-wise 3 times)\n");
            result.append(" => Possible Second: ").append(possibleSeconds.toString()).append(" (turn ccw past the 1st number)\n");
            result.append(" => Third: ").append(third).append(" (turn cw)");

            finalResult.setText(result.toString());

        } catch (NumberFormatException nfe) {
            // this should not happen
        }


    }

}
