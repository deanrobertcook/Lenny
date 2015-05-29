package com.example.deancook.lenny.pages.detail;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.pages.master.MasterFragment;
import com.example.deancook.lenny.models.Airline;
import com.example.deancook.lenny.stores.AirlineStore;

/**
 * Created by deancook on 24/05/15.
 */
public class DetailFragment extends Fragment implements AirlineStore.ItemObserver {

    public static final String TAG = DetailFragment.class.getName();
    private static final String ARG_AIRLINE_TAG = "ARG_AIRLINE_TAG";

    private Container container;

    public static DetailFragment newInstance(String airlineId) {
        DetailFragment fragment = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_AIRLINE_TAG, airlineId);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.container = (Container) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, parent, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        container.getAirlineStore().registerItemObserver(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        container.getAirlineStore().unregisterItemObserver(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAirlineHasChanged(Airline airline) {
        //Any data that the fragment needs is manipulated directly in any callbacks it specifies!

        View view = getView();
        ((TextView) view.findViewById(R.id.tv__fragment__name)).setText(airline.name);

        ((TextView) view.findViewById(R.id.tv__fragment__code)).setText(airline.code);

        ((TextView) view.findViewById(R.id.tv__fragment__phone)).setText(airline.phone);

        ((TextView) view.findViewById(R.id.tv__fragment__site)).setText(airline.site);
    }

    public interface Container {
        public AirlineStore getAirlineStore();
    }
}
