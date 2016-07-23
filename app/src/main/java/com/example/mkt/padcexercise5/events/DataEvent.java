package com.example.mkt.padcexercise5.events;

import com.example.mkt.padcexercise5.data.vos.AttractVO;

import java.util.List;

/**
 * Created by mkt on 7/13/2016.
 */
public class DataEvent {

    public static class AttractionDataLoadedEvent {
        private String extraMessage;
        private List<AttractVO> attractionVOList;

        public AttractionDataLoadedEvent(String extraMessage, List<AttractVO> mAttractionList) {
            this.extraMessage = extraMessage;
            this.attractionVOList = mAttractionList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<AttractVO> getAttractionList() {
            return attractionVOList;
        }
    }

    public static class DatePickedEvent { // immutable
        private String dateOfBrith;

        public DatePickedEvent(String dateOfBrith) {
            this.dateOfBrith = dateOfBrith;
        }

        public String getDateOfBrith() {
            return dateOfBrith;
        }
    }

    public static class RefreshUserLoginStatusEvent {

    }

}
