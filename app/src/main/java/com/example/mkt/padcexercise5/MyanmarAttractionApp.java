package com.example.mkt.padcexercise5;

import android.app.Application;
import android.content.Context;

/**
 * Created by mkt on 7/6/2016.
 */
public class MyanmarAttractionApp extends Application {

    public static final String TAG = "MyanmarAttractionsApp";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
