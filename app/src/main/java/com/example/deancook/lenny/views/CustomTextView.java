package com.example.deancook.lenny.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.deancook.lenny.MyApplication;
import com.example.deancook.lenny.R;
import com.example.deancook.lenny.TypefaceController;

/**
 * Created by deancook on 29/05/15.
 */
public class CustomTextView extends TextView {

    private int weightIndex;
    private int fontIndex;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomTextView,
                0, 0
        );

        try {
            fontIndex = typedArray.getInt(R.styleable.CustomTextView_font, 0);
            weightIndex = typedArray.getInt(R.styleable.CustomTextView_weight, 0);
        } finally {
            typedArray.recycle();
        }
        init(context);
    }

    private void init(Context context) {
        TypefaceController.CustomTypeface customTypeface =
                TypefaceController.CustomTypeface.values()[fontIndex];

        Typeface typeface = ((MyApplication)context.getApplicationContext())
                .getTypeFaceController()
                .getTypeFace(customTypeface);
        this.setTypeface(typeface);
    }
}
