package com.example.deancook.lenny;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.deancook.lenny.pages.MasterFragment;
import com.example.deancook.lenny.stores.AirlineStore;


public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getName();

    private AirlineStore airlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        airlines = new AirlineStore();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container__main, MasterFragment.newInstance(), MasterFragment.TAG)
                .commit();
    }

}
