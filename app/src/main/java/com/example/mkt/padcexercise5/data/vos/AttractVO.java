package com.example.mkt.padcexercise5.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mkt on 7/7/2016.
 */
public class AttractVO {

    @SerializedName("title")
    private String attractTitle;

    @SerializedName("desc")
    private String attractDesc;

    @SerializedName("images")
    private String[] image;

    public AttractVO(String attractTitle, String attractDesc) {
        this.attractTitle = attractTitle;
        this.attractDesc = attractDesc;
    }

    public String getAttractTitle() {
        return attractTitle;
    }

    public String getAttractDesc() {
        return attractDesc;
    }

    public String[] getImage() {
        return image;
    }
}
