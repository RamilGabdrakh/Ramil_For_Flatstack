package com.ramilforflatstack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramilforflatstack.R;

/**
 * Created by Ramil on 14.06.2015.
 */
public class NewsFeedFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_news_feed, container, false);

        return  view;
    }
}
