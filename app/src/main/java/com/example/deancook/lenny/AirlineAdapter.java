package com.example.deancook.lenny;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deancook.lenny.stores.Airline;

import java.util.List;

/**
 * Created by deancook on 20/05/15.
 */
public class AirlineAdapter extends BaseAdapter {

    Context context;

    protected List<Airline> airlines;
    LayoutInflater inflater;

    public AirlineAdapter(Context context, List<Airline> airlines) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.airlines = airlines;
    }

    @Override
    public int getCount() {
        return airlines.size();
    }

    @Override
    public Airline getItem(int i) {
        return airlines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return airlines.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(R.layout.list_element, viewGroup, false);
        }

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        Airline airline = airlines.get(i);
        nameTextView.setText(airline.name);

        return view;
    }
}
