package com.example.deancook.lenny.pages.detail;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.pages.master.MasterFragment;
import com.example.deancook.lenny.stores.Airline;
import com.example.deancook.lenny.stores.AirlineStore;

/**
 * Created by deancook on 24/05/15.
 */
public class DetailFragment extends Fragment implements AirlineStore.ItemObserver {

    public static final String TAG = DetailFragment.class.getName();
    private Airline airline;
    private MasterFragment.Container container;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.container = (MasterFragment.Container) activity;
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

        View view = getView();
        ((TextView) view.findViewById(R.id.tv__fragment__code)).setText(this.airline.code);
        ((TextView) view.findViewById(R.id.tv__fragment__name)).setText(this.airline.name);
        ((TextView) view.findViewById(R.id.tv__fragment__phone)).setText(this.airline.phone);
        ((TextView) view.findViewById(R.id.tv__fragment__site)).setText(this.airline.site);
    }

    @Override
    public void onStop() {
        container.getAirlineStore().unregisterItemObserver(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        this.viewHolder = null;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        this.airline = null;
        super.onDestroy();
    }

    @Override
    public void onAirlineHasChanged(Airline airline) {
        this.airline = airline;
    }

    private class ViewHolder {
        public TextView codeField;
        public TextView nameField;
        public TextView phoneField;
        public TextView siteField;

        public ViewHolder (
                TextView codeField,
                TextView nameField,
                TextView phoneField,
                TextView siteField
        ) {
            this.codeField = codeField;
            this.nameField = nameField;
            this.phoneField = phoneField;
            this.siteField = siteField;
        }
    }
}
