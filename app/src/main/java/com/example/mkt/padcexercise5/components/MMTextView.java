package com.example.mkt.padcexercise5.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.mkt.padcexercise5.utils.MMFontUtils;

/**
 * Created by mkt on 7/19/2016.
 */
public class MMTextView extends TextView {

    public MMTextView(Context context) {
        super(context);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }
}
