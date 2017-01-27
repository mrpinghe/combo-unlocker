package com.mrpinghe.android.combofinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment firstStep = MainActivityFragment.newInstance();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.main_act_layout, firstStep).commit();
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

    public void goToSecondStep(Integer firstP, Integer secondP, Double resisL) {
        Fragment thirdPicker = ThirdPickerFragment.newInstance(firstP, secondP, resisL);

        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_act_layout, thirdPicker).addToBackStack(null).commit();


    }


    public void showHelp(View v) {
        CharSequence cd = ((ImageView) v).getContentDescription();
        int choice = Integer.parseInt(cd.toString());
        int helpId = -1;
        switch (choice) {
            case 1:
                helpId = R.id.help_first_lp;
                break;
            case 2:
                helpId = R.id.help_second_lp;
                break;
            case 3:
                helpId = R.id.help_rl;
                break;
        }

        TextView tv = (TextView) v.getRootView().findViewById(helpId);
        tv.setVisibility(tv.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

    }
}
