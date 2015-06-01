package com.example.deancook.lenny;

import android.app.Application;
import android.graphics.Typeface;

import com.example.deancook.lenny.database.Contract;

/**
 * Created by deancook on 29/05/15.
 */
public class MyApplication extends Application{

    private TypefaceController typefaceController;

    public TypefaceController getTypeFaceController() {
        return typefaceController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(Contract.Airline.SQL_CREATE_TABLE);
        typefaceController = new TypefaceController(this);
    }

    @Override
    public void onTerminate() {
        typefaceController = null;
        super.onTerminate();
    }
}
