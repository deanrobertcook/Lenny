package com.example.deancook.lenny.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.deancook.lenny.MyApplication;
import com.example.deancook.lenny.TypefaceController;

/**
 * Created by deancook on 29/05/15.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface typeface = ((MyApplication)context.getApplicationContext())
                .getTypeFace(TypefaceController.CustomTypeface.CHANTELLI_REGULAR);
        this.setTypeface(typeface);
    }
}
