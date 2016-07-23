package com.example.mkt.padcexercise5.data.models;

import com.example.mkt.padcexercise5.data.agents.AttractionDataAgent;
import com.example.mkt.padcexercise5.data.retrofit.RetrofitDataAgent;
import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.example.mkt.padcexercise5.events.DataEvent;
import com.example.mkt.padcexercise5.utils.CommonInstances;
import com.example.mkt.padcexercise5.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by mkt on 7/7/2016.
 */
public class AttractModel {

    private static final String DUMMY_ATTRACT_LIST = "myanmar_attractions.json";

    private static AttractModel objInstance;

    private List<AttractVO> attractList;

    private AttractionDataAgent dataAgent;

    public AttractModel() {
        attractList = new ArrayList<>();
        dataAgent = RetrofitDataAgent.getInstance();
        dataAgent.loadAttractions();
        //attractList = initilizeAttractList();
    }

    public static AttractModel getObjInstance() {

        if (objInstance == null) {
            objInstance = new AttractModel();
        }
        return objInstance;
    }

    public List<AttractVO> initilizeAttractList() {

        List<AttractVO> attractList = new ArrayList<AttractVO>();
        try {
            String dummyAttractList = JsonUtils.getObjInstace().loadDummyData(DUMMY_ATTRACT_LIST);
            Type listtype = new TypeToken<List<AttractVO>>() {
            }.getType();
            attractList = CommonInstances.getGsonInstance().fromJson(dummyAttractList, listtype);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return attractList;
    }

    public List<AttractVO> getAttractList() {
        return attractList;
    }

    public AttractVO getAttractByTitle(String attractTitle) {
        for (AttractVO attract : attractList) {
            if (attract.getAttractTitle().equals(attractTitle)) {
                return attract;
            }
        }
        return null;
    }

    public void notifyAttractionsLoaded(List<AttractVO> attractionList) {
        //Notify that the data is ready - using LocalBroadcast
        attractList = attractionList;

        //keep the data in persistent layer.
        /*AttractionVO.saveAttractions(mAttractionList);*/

        broadcastAttractionLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingAttractions(String message) {

    }

    private void broadcastAttractionLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.AttractionDataLoadedEvent("extra-in-broadcast", attractList));
    }
}
