package com.ramilforflatstack.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramilforflatstack.R;
import com.ramilforflatstack.adapter.PhotoGridAdapter;
import com.ramilforflatstack.content.NewsFullItem;
import com.ramilforflatstack.tools.CropSquareTransformation;
import com.ramilforflatstack.tools.DateUtils;
import com.ramilforflatstack.tools.OttoBus;
import com.ramilforflatstack.tools.events.PhotoLoadet;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import droidkit.annotation.InjectView;

/**
 * Created by Ramil on 15.06.2015.
 */
public class NewsFullFragment extends Fragment {

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

    private PhotoGridAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_news_full, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        long autorId = getArguments().getLong("Autor_id");
        long postId = getArguments().getLong("PostId");

        NewsFullItem content = NewsFullItem.generateFullNewsItem(autorId, postId);
        Picasso.with(getActivity())
                .load(content.getPhotoUrl())
                .transform(new CropSquareTransformation())
                .placeholder(R.drawable.placeholder)
                .into(mCover);
        mTitle.setText(content.getTitle());
        mMessage.setText(content.getMessage());

        String date = DateUtils.getTextDate(content.getDate());
        mDate.setText(date);

        mAdapter = new PhotoGridAdapter(getActivity(), content.getAttachments());
        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        OttoBus.get().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        OttoBus.get().unregister(this);
    }

    @Subscribe
    public void onPhotoLoadet(PhotoLoadet event) {
        mAdapter.setGridViewHeightBasedOnChildren(mGridView, 3, 10);
    }

}
