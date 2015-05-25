package com.example.deancook.lenny;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;

/**
 * Created by deancook on 25/05/15.
 */
public class StoreFragment extends Fragment {

    public static final String TAG = StoreFragment.class.getName();

    private AirlineStore airlines;
    private Airline currentAirline;

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.airlines = new AirlineStore();
        airlines.fetchArlines();
        setRetainInstance(true);
    }

    public AirlineStore getAirlineStore() {
        return airlines;
    }

    public void setCurrentAirline(Airline airline) {
        this.currentAirline = airline;
    }

    public Airline getCurrentAirline() {
        return this.currentAirline;
    }
}
