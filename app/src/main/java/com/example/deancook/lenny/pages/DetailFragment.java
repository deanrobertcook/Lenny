package com.example.deancook.lenny.pages;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.stores.Airline;

/**
 * Created by deancook on 24/05/15.
 */
public class DetailFragment extends Fragment {
    public static final String TAG = DetailFragment.class.getName();
    private Container container;
    private Airline airline;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.container = (Container) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view;

        this.airline = this.container.getAirline();

        if (this.airline == null) {
            view = inflater.inflate(R.layout.fragment_detail_empty, parent, false);
        } else {
            view = inflater.inflate(R.layout.fragment_detail, parent, false);
            ((TextView) view.findViewById(R.id.code)).setText(this.airline.code);
            ((TextView) view.findViewById(R.id.name)).setText(this.airline.name);
            ((TextView) view.findViewById(R.id.phone)).setText(this.airline.phone);
            ((TextView) view.findViewById(R.id.site)).setText(this.airline.site);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        this.container = null;
        this.airline = null;
        super.onDestroy();
    }

    public interface Container {
        Airline getAirline();
    }

}
