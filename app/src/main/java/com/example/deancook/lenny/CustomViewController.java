package com.example.deancook.lenny;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deancook on 01/06/15.
 */
public class CustomViewController {
    public static final String TAG = CustomViewController.class.getName();
    private Context context;

    private enum CustomTypeface {
        CHANTELLI_REGULAR("Chantelli_Antiqua.ttf");

        public final String path;

        CustomTypeface(String path) {
            this.path = path;
        }
    }

    Map<CustomTypeface, Typeface> typefaceMap = new HashMap<>();

    public CustomViewController(Context context) {
        this.context = context;
    }

    public void tearDown() {
        this.context = null;
    }

    
}
