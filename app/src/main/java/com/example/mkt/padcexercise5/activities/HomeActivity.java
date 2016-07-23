package com.example.mkt.padcexercise5.activities;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mkt.padcexercise5.R;
import com.example.mkt.padcexercise5.controllers.UserController;
import com.example.mkt.padcexercise5.data.models.UserModel;
import com.example.mkt.padcexercise5.data.vos.AttractVO;
import com.example.mkt.padcexercise5.dialogs.SharedDialog;
import com.example.mkt.padcexercise5.events.DataEvent;
import com.example.mkt.padcexercise5.fragments.MyanmarAttractionFragment;
import com.example.mkt.padcexercise5.utils.MMFontUtils;
import com.example.mkt.padcexercise5.views.pods.ViewPodAccountControl;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MyanmarAttractionFragment.ControllerAttractItem,
        UserController,
        LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private ViewPodAccountControl vpAccountControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Menu leftMenu = navigationView.getMenu();
        MMFontUtils.applyMMFontToMenu(leftMenu);

        navigationView.setNavigationItemSelectedListener(this);

        vpAccountControl = (ViewPodAccountControl) navigationView.getHeaderView(0);
        vpAccountControl.setUserController(this);

        if (savedInstanceState == null) {
            MyanmarAttractionFragment attractionFragment = new MyanmarAttractionFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, attractionFragment)
                    .commit();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        UserModel.getObjInstance().init();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTapAttract(AttractVO attractVO) {
        Intent detail = AttractDetailActivity.newAttractIntent(attractVO.getAttractTitle());
        startActivity(detail);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (menuItem.getItemId()) {
                    case R.id.left_menu_myanmar_attraction_palces:

                        break;


                }
            }
        }, 100); //to close drawer smoothly.

        return true;
    }

    @Override
    public void onTapLogin() {
        Intent loginIntent = AccountControlActivity.newIntent(AccountControlActivity.NAVIGATE_TO_LOGIN);
        //startActivity(loginIntent);
        startActivityForResult(loginIntent, AccountControlActivity.RC_ACCOUNT_CONTROL_REGISTER);
    }

    @Override
    public void onTapRegister() {
        Intent intent = AccountControlActivity.newIntent(AccountControlActivity.NAVIGATE_TO_REGISTER);
        //startActivity(intent);
        startActivityForResult(intent, AccountControlActivity.RC_ACCOUNT_CONTROL_REGISTER);
    }

    @Override
    public void onTapLogout() {
        SharedDialog.confirmYesNoWithTheme(this, getString(R.string.msg_confirm_logout), new SharedDialog.YesNoConfirmDelegate() {
            @Override
            public void onConfirmYes() {
                UserModel.getObjInstance().logout();
            }

            @Override
            public void onConfirmNo() {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AccountControlActivity.RC_ACCOUNT_CONTROL_REGISTER) {
            if (resultCode == RESULT_OK) {
                boolean isRegisterSuccess = data.getBooleanExtra(AccountControlActivity.IR_IS_REGISTER_SUCCESS, false);
                boolean isLoginSuccess = data.getBooleanExtra(AccountControlActivity.IR_IS_LOGIN_SUCCESS, false);
                if (isRegisterSuccess) {
                    SharedDialog.promptMsgWithTheme(this, getString(R.string.msg_welcome_new_user));

                    DataEvent.RefreshUserLoginStatusEvent event = new DataEvent.RefreshUserLoginStatusEvent();
                    EventBus.getDefault().post(event);

                } else if(isLoginSuccess) {
                    SharedDialog.promptMsgWithTheme(this, getString(R.string.msg_welcom_login_user));

                    DataEvent.RefreshUserLoginStatusEvent event = new DataEvent.RefreshUserLoginStatusEvent();
                    EventBus.getDefault().post(event);
                }

            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
