package com.example.deancook.lenny;

import android.app.Application;
import android.graphics.Typeface;

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
        typefaceController = new TypefaceController(this);
    }

    @Override
    public void onTerminate() {
        typefaceController = null;
        super.onTerminate();
    }
}
