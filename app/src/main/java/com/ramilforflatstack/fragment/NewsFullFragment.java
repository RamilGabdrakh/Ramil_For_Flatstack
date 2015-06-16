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

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.ramilforflatstack.R;
import com.ramilforflatstack.adapter.PhotoGridAdapter;
import com.ramilforflatstack.content.NewsFullItem;
import com.ramilforflatstack.tools.DateUtils;
import com.ramilforflatstack.tools.GridInScrollHeight;
import com.ramilforflatstack.tools.OttoBus;
import com.ramilforflatstack.tools.events.PhotoLoadet;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_news_full, container, false);
        ButterKnife.inject(this, view);


        long autorId = getArguments().getLong("Autor_id");
        long postId = getArguments().getLong("PostId");

        NewsFullItem content = NewsFullItem.generateFullNewsItem(autorId, postId);
        UrlImageViewHelper.setUrlDrawable(mCover, content.getPhotoUrl(), R.drawable.placeholder);
        mTitle.setText(content.getTitle());
        mMessage.setText(content.getMessage());

        String date = DateUtils.getTextDate(content.getDate());
        mDate.setText(date);

        PhotoGridAdapter adapter = new PhotoGridAdapter(getActivity(), content.getAttachments());
        mGridView.setAdapter(adapter);

        return  view;
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GridInScrollHeight.setGridViewHeightBasedOnChildren(mGridView, 3, 0);
            }
        });
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
        GridInScrollHeight.setGridViewHeightBasedOnChildren(mGridView, 3, 0);
    }

}
