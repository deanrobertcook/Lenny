package com.example.deancook.lenny.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by deancook on 29/05/15.
 */
public class CustomTextView extends TextView {

    private static final String TYPEFACE_FILE = "Chantelli_Antiqua.ttf";

    public CustomTextView(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), TYPEFACE_FILE);
        this.setTypeface(typeface);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), TYPEFACE_FILE);
        this.setTypeface(typeface);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), TYPEFACE_FILE);
        this.setTypeface(typeface);
    }

//    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
