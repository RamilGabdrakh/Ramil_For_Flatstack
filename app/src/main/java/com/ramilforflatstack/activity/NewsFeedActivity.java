package com.ramilforflatstack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ramilforflatstack.R;
import com.ramilforflatstack.fragment.NewsFeedFragment;

/**
 * Created by Ramil on 14.06.2015.
 */
public class NewsFeedActivity extends VkActivity {

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, NewsFeedActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_feed);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NewsFeedFragment(), NewsFeedFragment.class.getName())
                .commit();
    }
}
