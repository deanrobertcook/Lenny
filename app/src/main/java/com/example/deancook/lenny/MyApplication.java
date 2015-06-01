package com.example.deancook.lenny;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by deancook on 29/05/15.
 */
public class MyApplication extends Application{

    private TypefaceController typefaceController;

    public Typeface getTypeFace(TypefaceController.CustomTypeface typeface) {
        return typefaceController.getTypeFace(typeface);
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
