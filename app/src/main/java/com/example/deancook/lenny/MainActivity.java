package com.example.deancook.lenny;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.deancook.lenny.pages.DetailFragment;
import com.example.deancook.lenny.pages.MasterFragment;
import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;


public class MainActivity extends FragmentActivity implements
        MasterFragment.Container,
        DetailFragment.Container {

    public static final String TAG = MainActivity.class.getName();

    private StoreFragment storeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storeFragment = (StoreFragment) this.getSupportFragmentManager()
                .findFragmentByTag(StoreFragment.TAG);

        if (storeFragment == null) {
            storeFragment = StoreFragment.newInstance();
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(storeFragment, StoreFragment.TAG)
                    .commit();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container__master, MasterFragment.newInstance(), MasterFragment.TAG)
                    .commit();

        } else {
            setContentView(R.layout.activity_main_landscape);
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container__master, MasterFragment.newInstance(), MasterFragment.TAG)
                    .add(R.id.container__detail, DetailFragment.newInstance(), DetailFragment.TAG)
                    .commit();
        }
    }

    /*
    The activity is now conforming to the interface expected by the fragment
     */
    @Override
    public AirlineStore getAirlineStore() {
        return storeFragment.getAirlineStore();
    }

    @Override
    public void onAirlineSelection(Airline airline) {
        Log.v(this.TAG, airline.toString());
        this.storeFragment.setCurrentAirline(airline);

        int containerToReplace;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            containerToReplace = R.id.container__master;
        } else {
            containerToReplace = R.id.container__detail;
        }

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerToReplace, DetailFragment.newInstance(), DetailFragment.TAG)
                .addToBackStack(MasterFragment.TAG)
                .commit();

    }

    @Override
    public Airline getAirline() {
        return this.storeFragment.getCurrentAirline();
    }
}
