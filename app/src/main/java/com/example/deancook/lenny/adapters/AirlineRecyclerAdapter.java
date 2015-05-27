package com.example.deancook.lenny.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.pages.MasterFragment;
import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by deancook on 23/05/15.
 */
public class AirlineRecyclerAdapter extends RecyclerView.Adapter<AirlineRecyclerAdapter.ViewHolder>
        implements AirlineStore.ListObserver {

    private List<Airline> airlines;
    private MasterFragment.Container container;
    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView airlineName;
        public int airlineId;

        public ViewHolder(final View airlineView) {
            super(airlineView);
            this.airlineName = (TextView) airlineView.findViewById(R.id.tv__fragment__name);
            airlineView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Airline airline = airlines.get(airlineId);
                    callback.onAirlineSelected(airline);
                }
            });
        }

        public void setId(int id) {
            this.airlineId = id;
        }
    }

    public AirlineRecyclerAdapter(MasterFragment.Container container) {
        this.airlines = new ArrayList<>();
        this.container = container;
    }

    @Override
    public void onAirlineListHasChanged(List<Airline> airlines) {
        this.airlines = airlines;

        //need to notify any views that the data set in the adapter
        //has changed
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.airline_name, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.airlineName.setText(airlines.get(position).name);
        holder.setId(position);
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
