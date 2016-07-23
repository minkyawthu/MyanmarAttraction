package com.example.mkt.padcexercise5.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.example.mkt.padcexercise5.fragments.MyanmarAttractionFragment;
import com.example.mkt.padcexercise5.utils.ConstantMyanmarAttraction;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mkt on 7/7/2016.
 */
public class AttractViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @Bind(R.id.tv_myanmarattraction_title)
    TextView tvAttractTitle;

    @Bind(R.id.tv_myanmarattraction_desc)
    TextView tvAttractDesc;

    @Bind(R.id.iv_photo) ImageView ivPhoto;

    private AttractVO attractVO;
    private MyanmarAttractionFragment.ControllerAttractItem controllerAttractItem;

    public AttractViewHolder(View itemView, MyanmarAttractionFragment.ControllerAttractItem mControllerAttractItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        controllerAttractItem = mControllerAttractItem;
    }

    public void setData(AttractVO attractVO) {
        this.attractVO = attractVO;
        tvAttractTitle.setText(attractVO.getAttractTitle());
        tvAttractDesc.setText(attractVO.getAttractDesc());

        String imageUrl = ConstantMyanmarAttraction.IMAGE_URL + attractVO.getImage()[0];

        Glide.with(ivPhoto.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.sample_photo)
                .error(R.drawable.sample_photo)
                .into(ivPhoto);

    }

    @Override
    public void onClick(View v) {
        controllerAttractItem.onTapAttract(attractVO);
    }
}
