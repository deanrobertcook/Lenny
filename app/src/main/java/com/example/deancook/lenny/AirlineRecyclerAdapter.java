package com.example.deancook.lenny;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by deancook on 23/05/15.
 */
public class AirlineRecyclerAdapter extends RecyclerView.Adapter<AirlineRecyclerAdapter.ViewHolder>
        implements AirlineStore.Observer {

    private List<Airline> airlines;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView airlineName;
        public ViewHolder(View airlineName) {
            super(airlineName);
            this.airlineName = (TextView) airlineName.findViewById(R.id.name);
        }
    }

    public AirlineRecyclerAdapter() {
        this.airlines = new ArrayList<>();
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
    }

    @Override
    public int getItemCount() {
        return airlines.size();
    }
}
