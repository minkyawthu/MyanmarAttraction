package com.example.mkt.padcexercise5.controllers;

/**
 * Created by mkt on 7/23/2016.
 */
public interface ControllerAccountControl {
    void onRegister(String name, String email, String password, String dateOfBirth, String country);
    void onLogin(String email, String password);
}
