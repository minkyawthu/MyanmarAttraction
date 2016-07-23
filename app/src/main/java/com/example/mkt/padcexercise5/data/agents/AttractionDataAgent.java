package com.example.mkt.padcexercise5.data.agents;

/**
 * Created by mkt on 7/13/2016.
 */
public interface AttractionDataAgent {
    void loadAttractions();
    void register(String name, String email, String password, String dateOfBirth, String country);
    void login(String email, String password);

}
