package com.example.deancook.lenny.pages.master;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.models.Airline;
import com.example.deancook.lenny.stores.AirlineStore;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by deancook on 23/05/15.
 */
public class AirlineRecyclerAdapter extends RecyclerView.Adapter<AirlineViewHolder>
        implements AirlineStore.ListObserver {

    private List<Airline> airlines = new ArrayList<>();
    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onAirlineListHasChanged(List<Airline> airlines) {
        this.airlines = airlines;

        //need to notify any views that the data set in the adapter
        //has changed
        notifyDataSetChanged();
    }

    @Override
    public AirlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.airline_name, parent, false);
        AirlineViewHolder holder = new AirlineViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AirlineViewHolder holder, int position) {
        holder.airlineName.setText(airlines.get(position).name);
        holder.setId(position);
        /*
         * So why do we create an anonymous callback class here instead of implementing the the
         * onAirlineSelected() method here in the class itself?
         */
        holder.setCallback(new AirlineViewHolder.Callback() {
            @Override
            public void onAirlineSelected(int airlinePosition) {
                callback.onAirlineSelected(airlines.get(airlinePosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return airlines.size();
    }

    /*
    This callback pattern is similar to the observer pattern except it only allows one
    observer at a time (whichever class implementing Callback which has called setCallback)
    In this case, it would be our Fragment, and that way, the Container (Activity) doesn't need
    to pierce all the way down, through our Fragment and into our Adapter
     */
    public interface Callback {
        public void onAirlineSelected(Airline airlineSelected);
    }
}
