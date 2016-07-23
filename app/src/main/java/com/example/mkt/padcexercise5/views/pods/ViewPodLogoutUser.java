package com.example.mkt.padcexercise5.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.controllers.BaseController;
import com.example.mkt.padcexercise5.controllers.UserController;
import com.example.mkt.padcexercise5.controllers.ViewController;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mkt on 7/18/2016.
 */
public class ViewPodLogoutUser extends RelativeLayout implements ViewController{

    private UserController userController;

    public ViewPodLogoutUser(Context context) {
        super(context);
    }

    public ViewPodLogoutUser(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodLogoutUser(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    @Override
    public void setController(BaseController baseController) {
        userController = (UserController) baseController;
    }

    @OnClick(R.id.btn_register)
    public void onTapRegister(View view) {
        userController.onTapRegister();
    }

    @OnClick(R.id.btn_login)
    public void onTapLogin(View view) {
        userController.onTapLogin();
    }

}
