package com.example.mkt.padcexercise5.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.mkt.padcexercise5.MyanmarAttractionApp;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.controllers.ControllerAccountControl;
import com.example.mkt.padcexercise5.data.models.UserModel;
import com.example.mkt.padcexercise5.dialogs.SharedDialog;
import com.example.mkt.padcexercise5.events.UserEvent;
import com.example.mkt.padcexercise5.fragments.LoginFragment;
import com.example.mkt.padcexercise5.fragments.RegisterFragment;
import com.example.mkt.padcexercise5.utils.ScreenUtils;
import com.example.mkt.padcexercise5.utils.SecurityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by mkt on 7/19/2016.
 */
public class AccountControlActivity extends AppCompatActivity implements ControllerAccountControl {

    public static final int NAVIGATE_TO_REGISTER = 1;
    public static final int NAVIGATE_TO_LOGIN = 2;
    public static final int RC_ACCOUNT_CONTROL_REGISTER = 100;

    private static final String IE_NAVIGATE_TO = "IE_NAVIGATE_TO";
    public static final String IR_IS_REGISTER_SUCCESS = "IR_IS_REGISTER_SUCCESS";

    public static final String IR_IS_LOGIN_SUCCESS = "IR_IS_LOGIN_SUCCESS";

    @Bind(R.id.iv_background)
    ImageView ivBackground;

    private int mNavigateTo;
    private ProgressDialog mProgressDialog;

    public static Intent newIntent(int navigateTo) {
        Intent intent = new Intent(MyanmarAttractionApp.getContext(), AccountControlActivity.class);
        intent.putExtra(IE_NAVIGATE_TO, navigateTo);
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        //ScreenUtils.setStatusbarColor(R.color.colorPrimaryDark, this);
        ButterKnife.bind(this, this);

        mNavigateTo = getIntent().getIntExtra(IE_NAVIGATE_TO, NAVIGATE_TO_REGISTER);

        if (savedInstanceState == null) {
            Fragment fragment;
            switch (mNavigateTo) {
                case NAVIGATE_TO_REGISTER:
                    fragment = RegisterFragment.newInstance();
                    break;
                case NAVIGATE_TO_LOGIN:
                    fragment = LoginFragment.newInstance();
                    break;
                default:
                    throw new RuntimeException("Unsupported Account Control Type : " + mNavigateTo);
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }

    }

    @Override
    public void onRegister(String name, String email, String password, String dateOfBirth, String country) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.msg_dialog_register));
        mProgressDialog.show();

        password = SecurityUtils.encryptMD5(password);
        UserModel.getObjInstance().register(name, email, password, dateOfBirth, country);
    }

    @Override
    public void onLogin(String email, String password) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.msg_dialog_login));
        mProgressDialog.show();

        password = SecurityUtils.encryptMD5(password);
        UserModel.getObjInstance().login(email, password);
    }

    //Success Register
    public void onEventMainThread(UserEvent.SuccessRegistrationEvent event) {
        Intent returningIntent = new Intent();
        returningIntent.putExtra(IR_IS_REGISTER_SUCCESS, true);
        setResult(Activity.RESULT_OK, returningIntent);
        finish();

        mProgressDialog.dismiss();
    }

    //Success Login
    public void onEventMainThread(UserEvent.SuccessLoginEvent event) {
        Intent returningIntent = new Intent();
        returningIntent.putExtra(IR_IS_LOGIN_SUCCESS, true);
        setResult(Activity.RESULT_OK, returningIntent);
        finish();

        mProgressDialog.dismiss();
    }

    //Failed to Register
    public void onEventMainThread(UserEvent.FailedRegistrationEvent event) {
        mProgressDialog.dismiss();
        SharedDialog.promptMsgWithTheme(this, event.getMessage());
    }
}
