package com.example.deancook.lenny;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deancook on 01/06/15.
 */
public class TypefaceController {
    public static final String TAG = TypefaceController.class.getName();
    private Context context;

    /*
    This enum essentially stores the paths to the files of any of the type faces that
    we might want to use in our application. This will help us only define them once.
     */
    public enum CustomTypeface {
        CHANTELLI_REGULAR("Chantelli_Antiqua.ttf");

        public final String path;

        CustomTypeface(String path) {
            this.path = path;
        }
    }

    /*
    Storing the type face here in a map, and keeping this map alive for the scope of the entire
    application keeps the typeface asset loaded in memory for the entire life of the application,
    speeding things up for the frequent redraws of our custom view elements, where each redrawn
    text view would have had to access the hard drive before this trick.

    Pretty good idea whenever you need to cache anything too- just use a HashMap!
     */
    Map<CustomTypeface, Typeface> typefaceMap = new HashMap<>();

    public TypefaceController(Context context) {
        this.context = context;
    }

    public void tearDown() {
        this.context = null;
    }

    public Typeface getTypeFace(CustomTypeface customTypeface) {
        if (typefaceMap.containsKey(customTypeface)) {
            return typefaceMap.get(customTypeface);
        }

        Typeface typeface = loadTypeface(customTypeface);
        return typeface;
    }

    private Typeface loadTypeface(CustomTypeface customTypeface) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), customTypeface.path);
        this.typefaceMap.put(customTypeface, typeface);
        return typeface;
    }
}
