package com.example.deancook.lenny.pages;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deancook.lenny.R;
import com.example.deancook.lenny.stores.AirlineStore;

/**
 * Created by deancook on 21/05/15.
 */
public class MasterFragment extends Fragment {

    public static final String TAG = MasterFragment.class.getName();
    private Container container;

    public static MasterFragment newInstance() {
        return new MasterFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        container = (Container) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onDetach() {
        container = null;
    }

    public interface Container {
        AirlineStore getAirlineStore();
    }
}
