package com.example.mkt.padcexercise5.events;

import com.example.mkt.padcexercise5.data.vos.UserVO;

/**
 * Created by mkt on 7/23/2016.
 */
public class UserEvent {

    public static class SuccessRegistrationEvent {
        private UserVO loginUser;

        public SuccessRegistrationEvent(UserVO loginUser) {
            this.loginUser = loginUser;
        }

        public UserVO getLoginUser() {
            return loginUser;
        }
    }

    public static class SuccessLoginEvent {
        private UserVO loginUser;

        public SuccessLoginEvent(UserVO loginUser) {
            this.loginUser = loginUser;
        }

        public UserVO getLoginUser() {
            return loginUser;
        }
    }

    public static class FailedRegistrationEvent {
        private String message;

        public FailedRegistrationEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
