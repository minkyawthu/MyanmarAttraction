package com.example.mkt.padcexercise5.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mkt.padcexercise5.R;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private String tabtitles[];

    public PagerAdapter(Context context, FragmentManager fragmentManager) {

        super(fragmentManager);
        tabtitles = context.getResources().getStringArray(R.array.login_register);

    }
    @Override
    public int getCount() {
        return tabtitles.length;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Fragment();
            case 1:
                return new Fragment();

            default:
                return null;
        }


    }

    @Override
    public CharSequence getPageTitle(int position) {
         return tabtitles[position];
    }

}
