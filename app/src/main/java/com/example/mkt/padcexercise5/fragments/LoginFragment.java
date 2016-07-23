package com.example.mkt.padcexercise5.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mkt.padcexercise5.MyanmarAttractionApp;
import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.adapters.PagerAdapter;
import com.example.mkt.padcexercise5.controllers.ControllerAccountControl;
import com.example.mkt.padcexercise5.utils.ConstantMyanmarAttraction;
import com.example.mkt.padcexercise5.views.PasswordVisibilityListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mkt on 7/16/2016.
 */
public class LoginFragment extends Fragment {

    @Bind(R.id.lbl_login_title)
    TextView lblLoginTitle;

    @Bind(R.id.et_email)
    EditText etEmail;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.btn_login)
    Button btnLogin;

    private ControllerAccountControl mControllerAccountControl;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerAccountControl = (ControllerAccountControl) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        lblLoginTitle.setText(Html.fromHtml(getString(R.string.lbl_login_title)));
        etPassword.setOnTouchListener(new PasswordVisibilityListener());

        return view;
    }

    @OnClick(R.id.btn_login)
    public void onTapLogin(Button login) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(email)) {
                etEmail.setError(getString(R.string.error_missing_email));
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError(getString(R.string.error_missing_password));
            }
        } else if (!isEmailValid(email)) {
            //Email address is not in the right format.
            etEmail.setError(getString(R.string.error_email_is_not_valid));
        } else {
            //Checking on client side is done here.
            mControllerAccountControl.onLogin(email, password);
        }

    }

    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(ConstantMyanmarAttraction.EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

}
