package com.example.mkt.padcexercise5.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mkt.padcexercise5.MyanmarAttractionApp;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.example.mkt.padcexercise5.fragments.MyanmarAttractionFragment;
import com.example.mkt.padcexercise5.views.holders.AttractViewHolder;

import java.util.List;

/**
 * Created by mkt on 7/7/2016.
 */
public class AttractAdapter extends RecyclerView.Adapter<AttractViewHolder> {

    private LayoutInflater inflater;
    private List<AttractVO> attractList;
    private MyanmarAttractionFragment.ControllerAttractItem controllerAttractItem;

    public void setNewData(List<AttractVO> newAttractionList) {
        attractList = newAttractionList;
        notifyDataSetChanged();
    }

    public AttractAdapter(List<AttractVO> attractList, MyanmarAttractionFragment.ControllerAttractItem controllerAttractItem) {
        inflater = LayoutInflater.from(MyanmarAttractionApp.getContext());
        this.attractList = attractList;
        this.controllerAttractItem = controllerAttractItem;

    }

    @Override
    public AttractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_item_attract, parent, false);
        AttractViewHolder attractViewHolder = new AttractViewHolder(view, controllerAttractItem);
        return attractViewHolder;
    }

    @Override
    public void onBindViewHolder(AttractViewHolder holder, int position) {
        holder.setData(attractList.get(position));
    }

    @Override
    public int getItemCount() {
        return attractList.size();
    }
}
