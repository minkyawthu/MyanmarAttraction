package com.example.mkt.padcexercise5.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mkt.padcexercise5.MyanmarAttractionApp;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.views.items.ViewItemCountry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkt on 7/19/2016.
 */
public class CountryListAdapter extends BaseAdapter {

    private List<String> mCountryList;
    private LayoutInflater mInflater;

    public CountryListAdapter(List<String> countryList) {
        if (countryList != null) {
            this.mCountryList = countryList;
        } else {
            this.mCountryList = new ArrayList<>();
        }
        mInflater = LayoutInflater.from(MyanmarAttractionApp.getContext());
    }

    @Override
    public int getCount() {
        return mCountryList.size();
    }

    @Override
    public String getItem(int position) {
        return mCountryList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.view_item_country, parent, false);
        }

        if (convertView instanceof ViewItemCountry) {
            ViewItemCountry viCountry = (ViewItemCountry) convertView;
            viCountry.setData(getItem(position));
        }

        return convertView;
    }
}