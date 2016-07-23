package com.example.mkt.padcexercise5.data.models;

import com.example.mkt.padcexercise5.data.vos.UserVO;
import com.example.mkt.padcexercise5.events.DataEvent;
import com.example.mkt.padcexercise5.events.UserEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by mkt on 7/16/2016.
 */
public class UserModel extends BaseModel {

    private static UserModel objInstance;

    private UserVO loginUser;

    public UserModel() {
    }

    public static UserModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new UserModel();
        }
        return objInstance;
    }

    public void init(){
        loginUser = UserVO.loadLoginUser();

        if(loginUser != null){
            DataEvent.RefreshUserLoginStatusEvent event = new DataEvent.RefreshUserLoginStatusEvent();
            EventBus.getDefault().postSticky(event);
        }
    }

    public void register(String name, String email, String password, String dateOfBirth, String country) {
        dataAgent.register(name, email, password, dateOfBirth, country);
    }

    public void login(String email, String password) {
        dataAgent.login(email, password);
    }

    //Success Register :: inherited from BaseModel::EventBus
    public void onEventMainThread(UserEvent.SuccessRegistrationEvent event) {
        loginUser = event.getLoginUser();

        loginUser.saveLoginUser();

    }

    //Success Login
    public void onEventMainThread(UserEvent.SuccessLoginEvent event) {
        loginUser = event.getLoginUser();

        //loginUser.saveLoginUser();
        // TODO Persist login user object.
    }

    //Failed to Register
    public void onEventMainThread(UserEvent.FailedRegistrationEvent event) {
        //Do nothing on persistent layer.
    }

    public UserVO getLoginUser(){
        return loginUser;
    }

    public boolean isUserLogin() {
        return loginUser != null;
    }

    public void logout() {
        loginUser.clearData();
        loginUser = null;

        DataEvent.RefreshUserLoginStatusEvent event = new DataEvent.RefreshUserLoginStatusEvent();
        EventBus.getDefault().post(event);
    }

}
