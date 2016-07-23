package com.example.mkt.padcexercise5.data.retrofit;

import com.example.mkt.padcexercise5.data.responses.AttractionListResponse;
import com.example.mkt.padcexercise5.data.responses.RegisterResponse;
import com.example.mkt.padcexercise5.utils.ConstantMyanmarAttraction;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mkt on 7/13/2016.
 */
public interface AttractionApi {

    @FormUrlEncoded
    @POST(ConstantMyanmarAttraction.API_GET_ATTRACTION_LIST)
    Call<AttractionListResponse> loadAttractions(
            @Field(ConstantMyanmarAttraction.PARAM_ACCESS_TOKEN) String param);

    @FormUrlEncoded
    @POST(ConstantMyanmarAttraction.API_REGISTER)
    Call<RegisterResponse> register(
            @Field(ConstantMyanmarAttraction.PARAM_NAME) String name,
            @Field(ConstantMyanmarAttraction.PARAM_EMAIL) String email,
            @Field(ConstantMyanmarAttraction.PARAM_PASSWORD) String password,
            @Field(ConstantMyanmarAttraction.PARAM_DATE_OF_BIRTH) String dateOfBirth,
            @Field(ConstantMyanmarAttraction.PARAM_COUNTRY_OF_ORIGIN) String country
    );

    @FormUrlEncoded
    @POST(ConstantMyanmarAttraction.API_LOGIN)
    Call<RegisterResponse> login(
            @Field(ConstantMyanmarAttraction.PARAM_EMAIL) String email,
            @Field(ConstantMyanmarAttraction.PARAM_PASSWORD) String password
    );
}
