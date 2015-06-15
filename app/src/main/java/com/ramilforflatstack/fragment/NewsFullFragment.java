package com.ramilforflatstack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.ramilforflatstack.R;
import com.ramilforflatstack.Utils.DateUtils;
import com.ramilforflatstack.Utils.GridInScrollHeight;
import com.ramilforflatstack.adapter.PhotoGridAdapter;
import com.ramilforflatstack.content.NewsFullItem;
import com.ramilforflatstack.model.FeedItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Ramil on 15.06.2015.
 */
public class NewsFullFragment extends Fragment {

    public static final String FEED_ITEM_KEY = "feed_item";

    @InjectView(R.id.photo_grid_view)
    GridView mGridView;

    @InjectView(R.id.cover)
    ImageView mCover;

    @InjectView(R.id.title)
    TextView mTitle;

    @InjectView(R.id.date)
    TextView mDate;

    @InjectView(R.id.message)
    TextView mMessage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_news_feed, container, false);
        ButterKnife.inject(this, view);

        NewsFullItem content = null;
        UrlImageViewHelper.setUrlDrawable(mCover, content.getPhotoUrl());
        mTitle.setText(content.getTitle());
        mMessage.setText(content.getMessage());

        String date = DateUtils.getTextDate(content.getDate());
        mDate.setText(date);

        PhotoGridAdapter adapter = new PhotoGridAdapter(getActivity(), content.getAttachments());
        mGridView.setAdapter(adapter);
        GridInScrollHeight.setGridViewHeightBasedOnChildren(mGridView, 3, 10);

        return  view;
    }
}
