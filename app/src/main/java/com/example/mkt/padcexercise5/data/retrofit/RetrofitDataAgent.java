package com.example.mkt.padcexercise5.data.retrofit;

import com.example.mkt.padcexercise5.data.agents.AttractionDataAgent;
import com.example.mkt.padcexercise5.data.models.AttractModel;
import com.example.mkt.padcexercise5.data.responses.AttractionListResponse;
import com.example.mkt.padcexercise5.data.responses.RegisterResponse;
import com.example.mkt.padcexercise5.events.UserEvent;
import com.example.mkt.padcexercise5.utils.CommonInstances;
import com.example.mkt.padcexercise5.utils.ConstantMyanmarAttraction;

import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mkt on 7/13/2016.
 */
public class RetrofitDataAgent implements AttractionDataAgent {

    private static RetrofitDataAgent objInstance;

    private final AttractionApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantMyanmarAttraction.ATTRACTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(AttractionApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadAttractions() {
        Call<AttractionListResponse> loadAttractionCall = theApi.loadAttractions(ConstantMyanmarAttraction.ACCESS_TOKEN);
        loadAttractionCall.enqueue(new Callback<AttractionListResponse>() {
            @Override
            public void onResponse(Call<AttractionListResponse> call, Response<AttractionListResponse> response) {
                AttractionListResponse attractionListResponse = response.body();
                if (attractionListResponse == null) {
                    AttractModel.getObjInstance().notifyErrorInLoadingAttractions(response.message());
                } else {
                    AttractModel.getObjInstance().notifyAttractionsLoaded(attractionListResponse.getAttractionList());
                }
            }

            @Override
            public void onFailure(Call<AttractionListResponse> call, Throwable throwable) {
                AttractModel.getObjInstance().notifyErrorInLoadingAttractions(throwable.getMessage());
            }
        });
    }

    @Override
    public void register(String name, String email, String password, String dateOfBirth, String countryOfOrigin) {
        Call<RegisterResponse> registerCall = theApi.register(name, email, password, dateOfBirth, countryOfOrigin);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if(registerResponse == null || registerResponse.getCode() == ConstantMyanmarAttraction.RESPONSE_CODE_FAILED) {
                    UserEvent.FailedRegistrationEvent event = new UserEvent.FailedRegistrationEvent(response.message());
                    EventBus.getDefault().post(event);
                } else {
                    UserEvent.SuccessRegistrationEvent event = new UserEvent.SuccessRegistrationEvent(registerResponse.getLoginUser());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable throwable) {
                UserEvent.FailedRegistrationEvent event = new UserEvent.FailedRegistrationEvent(throwable.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }

    @Override
    public void login(String email, String password) {
        Call<RegisterResponse> loginCall = theApi.login(email, password);
        loginCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse loginResponse = response.body();
                if(loginResponse == null || loginResponse.getCode() == ConstantMyanmarAttraction.RESPONSE_CODE_FAILED) {
                    UserEvent.FailedRegistrationEvent event = new UserEvent.FailedRegistrationEvent(response.message());
                    EventBus.getDefault().post(event);
                } else {
                    UserEvent.SuccessLoginEvent event = new UserEvent.SuccessLoginEvent(loginResponse.getLoginUser());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                UserEvent.FailedRegistrationEvent event = new UserEvent.FailedRegistrationEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });

    }
}
