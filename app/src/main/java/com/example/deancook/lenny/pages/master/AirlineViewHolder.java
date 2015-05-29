package com.example.deancook.lenny.pages.master;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.deancook.lenny.R;

/**
 * Created by deancook on 28/05/15.
 */
public class AirlineViewHolder extends RecyclerView.ViewHolder {
    public TextView airlineName;
    private int airlineId;
    private Callback callback;

    public AirlineViewHolder(final View airlineView) {
        super(airlineView);
        this.airlineName = (TextView) airlineView.findViewById(R.id.tv__fragment__name);
        airlineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onAirlineSelected(airlineId);
            }
        });
    }

    public void setId(int id) {
        this.airlineId = id;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    /*
     * It's important to remember that a class only ever uses the callbacks that IT sets,
     * It only uses interfaces defined by itself!
     */
    public interface Callback {
        public void onAirlineSelected(int AirlineSelected);
    }
}


