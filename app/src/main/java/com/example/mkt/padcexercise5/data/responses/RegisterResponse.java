package com.example.mkt.padcexercise5.data.responses;

import com.example.mkt.padcexercise5.data.vos.UserVO;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mkt on 7/23/2016.
 */
public class RegisterResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("login_user")
    private UserVO loginUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UserVO getLoginUser() {
        return loginUser;
    }
}
