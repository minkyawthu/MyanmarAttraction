package com.example.mkt.padcexercise5.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.controllers.UserController;
import com.example.mkt.padcexercise5.data.models.UserModel;
import com.example.mkt.padcexercise5.events.DataEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by mkt on 7/18/2016.
 */
public class ViewPodAccountControl extends FrameLayout {

    @Bind(R.id.vp_login_user)
    ViewPodLoginUser vpLoginUser;

    @Bind(R.id.vp_logout_user)
    ViewPodLogoutUser vpLogoutUser;

    public ViewPodAccountControl(Context context) {
        super(context);
    }

    public ViewPodAccountControl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodAccountControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }

        refreshUserLoginStatus();

    }

    private void refreshUserLoginStatus() {
        boolean isUserLogin = UserModel.getObjInstance().isUserLogin();
        vpLogoutUser.setVisibility(isUserLogin ? View.GONE : View.VISIBLE);
        vpLoginUser.setVisibility(isUserLogin ? View.VISIBLE : View.GONE);

        if (isUserLogin) {
            vpLoginUser.setData(UserModel.getObjInstance().getLoginUser());
        }
    }

    public void setUserController(UserController userController) {
        vpLogoutUser.setController(userController);
        vpLoginUser.setController(userController);
    }

    public void onEventMainThread(DataEvent.RefreshUserLoginStatusEvent event) {
        refreshUserLoginStatus();
    }
}
