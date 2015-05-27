package com.example.deancook.lenny;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.deancook.lenny.pages.DetailFragment;
import com.example.deancook.lenny.pages.MasterFragment;
import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;


public class MainActivity extends FragmentActivity implements
        MasterFragment.Container {

    public static final String TAG = MainActivity.class.getName();

    private AirlineStore airlineStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        airlineStore = new AirlineStore();
        airlineStore.fetchArlines();

        /*
        The (Support)FragmentManager handles all of the fragments attached to the activity
        We are starting a "transaction" with the fragment we want to add to the activity. We
        must then specify the container (activity inner layout) that we want to attach it to.
        We then get the activity and supply a tag that we can use to fetch the fragment later
        when it gets moved to the back stack etc.
         */
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container__main, MasterFragment.newInstance(), MasterFragment.TAG)
                .commit();
    }

    /*
    The activity is now conforming to the interface expected by the fragment
     */
    @Override
    public AirlineStore getAirlineStore() {
        return airlineStore;
    }

    @Override
    public void onAirlineSelection(Airline airline) {
        Log.v(this.TAG, airline.toString());
        this.airlineStore.setAirline(airline);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container__main, DetailFragment.newInstance(), DetailFragment.TAG)
                .addToBackStack(MasterFragment.TAG)
                .commit();

    }
}
