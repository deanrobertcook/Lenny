package com.example.deancook.lenny.pages.master;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.models.Airline;
import com.example.deancook.lenny.stores.AirlineStore;

/**
 * Created by deancook on 21/05/15.
 */
public class MasterFragment extends Fragment implements AirlineRecyclerAdapter.Callback {

    public static final String TAG = MasterFragment.class.getName();

    private Container container;
    private AirlineRecyclerAdapter adapter;

    public static MasterFragment newInstance() {
        return new MasterFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        container = (Container) activity;
    }

    /*
    This method is called when it's time to contribute the Fragment's layout to the
    activity that it is attached to
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master, parent, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        //Should be no need to worry about the layout manager or the view, so we don't make them global
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerLayoutManager);

        //the adapter we will need to refer to throughout the lifecycle!
        adapter = new AirlineRecyclerAdapter();
        adapter.setCallback(this);
        adapter.setFont(container.getFont());

        recyclerView.setAdapter(adapter);


        return rootView;
    }

    /*
    Good practice to worry about the fragments data in the onStart/onStop methods
    The adapter should be registered here, and unregistered again in the onStop() method.
    In order to have full control and awareness over the lifecyle and what's happening, it's
    a good idea to implement the methods and their inverse
     */
    @Override
    public void onStart() {
        super.onStart();
        //We shouldn't set another variable to hold the airlines store here (like this.arlines or anything
        //like that). Instead, we just get the list straight from the store
        this.container.getAirlineStore().registerListObserver(this.adapter);
    }

    /*
    Here's a good place to tell the adapter that we're listening to it when the user interacts with
    it's data. We pass the Fragment down as a Callback, and the Callback interface specifies what
    methods we need to implement in order to handle events on ViewHolder View items.
     */
    @Override
    public void onResume() {
        super.onResume();
        this.adapter.setCallback(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.adapter.setCallback(null);
    }

    @Override
    public void onStop() {
        this.container.getAirlineStore().unregisterListObserver(this.adapter);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        this.adapter = null;
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        //prevents memory leaking ??
        container = null;
        super.onDetach();
    }

    @Override
    public void onAirlineSelected(Airline airlineSelected) {
        this.container.onAirlineSelection(airlineSelected);
    }

    /*
    Here, we are creating an interface through which the Fragment will communicate with the outside
    world. That way, any object (activity or otherwise) will need to conform to this interface (or
    else we get a CastException), meaning that the Fragment will be more reusable
     */
    public interface Container {
        AirlineStore getAirlineStore();

        void onAirlineSelection(Airline airline);

        Typeface getFont();
    }
}
