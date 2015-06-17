package com.ramilforflatstack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.ramilforflatstack.R;
import com.ramilforflatstack.fragment.NewsFeedFragment;
import com.vk.sdk.VKSdk;

import droidkit.annotation.InjectView;
import droidkit.annotation.OnClick;

/**
 * Created by Ramil on 14.06.2015.
 */
public class NewsFeedActivity extends VkActivity implements View.OnClickListener {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.drawer)
    DrawerLayout mNavDrawer;

    public static void startForResult(Activity activity, int request) {
        activity.startActivityForResult(new Intent(activity, NewsFeedActivity.class), request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_feed);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white_36dp);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NewsFeedFragment(), NewsFeedFragment.class.getName())
                .commit();

        mToolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mNavDrawer.openDrawer(Gravity.LEFT);
    }

    @OnClick(R.id.logout_view)
    public void onLogoutClick(View v) {
        VKSdk.logout();
        setResult(MainActivity.REAUHTORIZE);
        finish();
    }
}
