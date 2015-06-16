package com.ramilforflatstack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ramilforflatstack.R;
import com.ramilforflatstack.fragment.NewsFullFragment;

import droidkit.annotation.InjectView;

/**
 * Created by Ramil on 14.06.2015.
 */
public class NewsActivity extends VkActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    public static void start(Activity activity, long autorId, long postId) {
        Intent intent  = new Intent(activity, NewsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("Autor_id", autorId);
        bundle.putLong("PostId", postId);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_feed);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);

        NewsFullFragment fragment = new NewsFullFragment();
        fragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, NewsFullFragment.class.getName())
                .commit();
    }
}
