package com.example.mkt.padcexercise5.data.responses;

import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mkt on 7/13/2016.
 */
public class AttractionListResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("attraction")
    private ArrayList<AttractVO> attractionList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<AttractVO> getAttractionList() {
        return attractionList;
    }
}
