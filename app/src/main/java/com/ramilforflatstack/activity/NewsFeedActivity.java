package com.ramilforflatstack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ramilforflatstack.R;
import com.ramilforflatstack.fragment.NewsFeedFragment;

import droidkit.annotation.InjectView;

/**
 * Created by Ramil on 14.06.2015.
 */
public class NewsFeedActivity extends VkActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    public static void start(Activity activity, int request) {
        activity.startActivityForResult(new Intent(activity, NewsFeedActivity.class), request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_feed);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_close_white_36dp);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NewsFeedFragment(), NewsFeedFragment.class.getName())
                .commit();
    }
}
