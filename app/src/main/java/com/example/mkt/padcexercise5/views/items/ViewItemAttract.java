package com.example.mkt.padcexercise5.views.items;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mkt.padcexercise5.R;

/**
 * Created by mkt on 7/7/2016.
 */
public class ViewItemAttract extends CardView {

    private TextView tvAttractTitle;
    private ImageView ivStockPhoto;
    private TextView tvAtractDesc;

    public ViewItemAttract(Context context) {
        super(context);
    }

    public ViewItemAttract(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewItemAttract(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvAttractTitle = (TextView) findViewById(R.id.tv_myanmarattraction_title);
        ivStockPhoto = (ImageView) findViewById(R.id.iv_photo);
        tvAtractDesc = (TextView) findViewById(R.id.tv_myanmarattraction_desc);

    }
}
