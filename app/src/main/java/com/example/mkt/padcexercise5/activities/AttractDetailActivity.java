package com.example.mkt.padcexercise5.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mkt.padcexercise5.MyanmarAttractionApp;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.data.models.AttractModel;
import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.example.mkt.padcexercise5.utils.ConstantMyanmarAttraction;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mkt on 7/7/2016.
 */
public class AttractDetailActivity extends AppCompatActivity {

    private static final String IE_ATTRACT_TITLE = "IE_ATTRACT_TITLE";

    @Bind(R.id.iv_attract_backdrop)
    ImageView ivAttractPhoto;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.tv_attract_desc)
    TextView tvAttractDesc;

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    public static Intent newAttractIntent(String title) {

        Intent attractDetail = new Intent(MyanmarAttractionApp.getContext(), AttractDetailActivity.class);
        attractDetail.putExtra(IE_ATTRACT_TITLE, title);
        return attractDetail;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attract_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_accent_24dp);
        }

        String attractTitle = getIntent().getStringExtra(IE_ATTRACT_TITLE);

        AttractVO attract = AttractModel.getObjInstance().getAttractByTitle(attractTitle);
        if (attract == null) {
            throw new RuntimeException("Can't find Event obj with the title : "+attractTitle);
        } else {
            collapsingToolbar.setTitle(attract.getAttractTitle());
            tvAttractDesc.setText("\n\n" + "\n\n" + attract.getAttractDesc() + "\n\n" +
                    attract.getAttractDesc() + "\n\n" +
                    attract.getAttractDesc());

            String imageUrl = ConstantMyanmarAttraction.IMAGE_URL + attract.getImage()[0];

            Glide.with(ivAttractPhoto.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.sample_photo)
                    .error(R.drawable.sample_photo)
                    .into(ivAttractPhoto);

            collapsingToolbar.setTitle(attractTitle);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = MyanmarAttractionApp.getContext();
            String transitionName = context.getResources().getString(R.string.attraction_list_detail_transition_name);
            ivAttractPhoto.setTransitionName(transitionName);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
