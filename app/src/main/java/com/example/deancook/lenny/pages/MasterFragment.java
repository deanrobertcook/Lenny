package com.example.deancook.lenny.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deancook.lenny.AirlineRecyclerAdapter;
import com.example.deancook.lenny.R;
import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;

/**
 * Created by deancook on 21/05/15.
 */
public class MasterFragment extends Fragment {

    public static final String TAG = MasterFragment.class.getName();

    private Container container;
    private AirlineStore airlines;
    private RecyclerView recyclerView;

    public static MasterFragment newInstance() {
        return new MasterFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        container = (Container) activity;
        airlines = container.getAirlineStore();
    }

    /*
    This method is called when it's time to contribute the Fragment's layout to the
    activity that it is attached to
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master, parent, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerLayoutManager);

        AirlineRecyclerAdapter recyclerAdapter = new AirlineRecyclerAdapter(this.container);
        airlines.registerObserver(recyclerAdapter);
        recyclerView.setAdapter(recyclerAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        //prevents memory leaking ??
        container = null;
        super.onDetach();
    }

    /*
    Here, we are creating an interface through which the Fragment will communicate with the outside
    world. That way, any object (activity or otherwise) will need to conform to this interface (or
    else we get a CastException), meaning that the Fragment will be more reusable
     */
    public interface Container {
        AirlineStore getAirlineStore();

        void onAirlineSelection(Airline airline);
    }
}
