package com.example.mkt.padcexercise5.views.items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mkt.padcexercise5.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mkt on 7/19/2016.
 */
public class ViewItemCountry  extends FrameLayout {

    @Bind(R.id.tv_country)
    TextView tvCountry;

    public ViewItemCountry(Context context) {
        super(context);
    }

    public ViewItemCountry(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemCountry(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String country) {
        tvCountry.setText(country);
    }
}
