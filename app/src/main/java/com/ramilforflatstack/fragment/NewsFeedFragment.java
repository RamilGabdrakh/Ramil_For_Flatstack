package com.ramilforflatstack.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.ramilforflatstack.R;
import com.ramilforflatstack.adapter.NewsFeedItemAdapter;
import com.ramilforflatstack.content.NewsFeedItem;
import com.ramilforflatstack.response.NewsFeedResponse;
import com.ramilforflatstack.response.NewsFeedResponseContent;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Ramil on 14.06.2015.
 */
public class NewsFeedFragment extends Fragment implements SwipyRefreshLayout.OnRefreshListener {

    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @InjectView(R.id.swipyrefreshlayout)
    SwipyRefreshLayout mSwipeLayout;

    private Gson mGson = new Gson();
    private SwipyRefreshLayoutDirection mDirection = SwipyRefreshLayoutDirection.TOP;
    private NewsFeedItemAdapter mNewsFeedItemAdapter;
    List<NewsFeedItem> items = new ArrayList<>();
    VKRequest mRequest;
    long mEndTime;

    protected static final int DELAY_SEC = 3000;
    private Handler mHandler = new Handler();
    private Runnable mPostRequest = new Runnable() {
        @Override
        public void run() {
            mRequest = createRequest(mDirection);
            mRequest.executeWithListener(new VkListener());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_news_feed, container, false);
        ButterKnife.inject(this, view);

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mNewsFeedItemAdapter = new NewsFeedItemAdapter(items, getActivity());

        mSwipeLayout.setRefreshing(true);
        mEndTime = System.currentTimeMillis()/1000;
        String endTime = Long.toString(mEndTime);
        mRequest = new VKRequest("newsfeed.get", VKParameters.from("filters", "post", "count", "3", "end_time", endTime));
        mRequest.executeWithListener(new VkListener());

        mRecyclerView.setAdapter(mNewsFeedItemAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume(){
        super.onResume();
        mSwipeLayout.setOnRefreshListener(this);
    }

    @Override
    public void onPause(){
        mSwipeLayout.setOnRefreshListener(null);
        super.onPause();
    }

    @Override
    public void onDetach() {
        mHandler.removeCallbacks(mPostRequest);
        super.onDetach();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        Log.d("mytag", "Refresh triggered at "
                + (direction == SwipyRefreshLayoutDirection.TOP ? "top" : "bottom"));
        mDirection = direction;
        mRequest = createRequest(direction);
        mRequest.executeWithListener(new VkListener());
    }

    private VKRequest createRequest(SwipyRefreshLayoutDirection direction) {
        String endTime;
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            mEndTime = System.currentTimeMillis()/1000;
            Log.d("mytag","end_time  = " + mEndTime);
        } else {
            mEndTime = mNewsFeedItemAdapter.getLastItem().getDate();
            Log.d("mytag","end_time  = " + mEndTime);

        }
        Log.d("mytag","params  = " + mRequest.getMethodParameters().toString());

        endTime = Long.toString(mEndTime);
        return new VKRequest("newsfeed.get", VKParameters.from("filters", "post", "count", "3", "end_time", endTime));
    }

    private class VkListener extends VKRequest.VKRequestListener {
        @Override
        public void onComplete(VKResponse response) {
            super.onComplete(response);

            NewsFeedResponse resp = mGson.fromJson( response.responseString, NewsFeedResponse.class );
            NewsFeedResponseContent content = resp.content;
            Log.d("mytag", "resp" + response.responseString);

            List<NewsFeedItem> newItems = content.toNewsList();

            if(mEndTime < newItems.get(0).getDate()) {
                mHandler.postDelayed(mPostRequest, DELAY_SEC);
            } else {
                content.save();
                if (mDirection == SwipyRefreshLayoutDirection.TOP) {
                    items.clear();
                    items.addAll(newItems);
                } else {
                    //merge

                    long lastPostId = mNewsFeedItemAdapter.getLastItem().getPostId();
                    boolean hasThisPost = false;
                    int i = 0;
                    while(!hasThisPost && i < newItems.size()) {
                        hasThisPost = lastPostId == newItems.get(i).getPostId();
                        i++;
                    }
                    if (hasThisPost) {
                        while (lastPostId != newItems.get(0).getPostId()) {
                            newItems.remove(0);
                        }
                        newItems.remove(0);
                    }
                    items.addAll(newItems);
                }

                mNewsFeedItemAdapter.notifyDataSetChanged();
                mSwipeLayout.setRefreshing(false);
            }
        }
    }
}
