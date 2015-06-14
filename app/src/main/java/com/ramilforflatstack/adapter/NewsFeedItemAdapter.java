package com.ramilforflatstack.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.ramilforflatstack.R;
import com.ramilforflatstack.content.NewsFeedItem;

import java.util.List;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedItemAdapter extends RecyclerView.Adapter<NewsFeedItemAdapter.ViewHolder> {


    private List<NewsFeedItem> mItems;
    private Activity mActivity;

    public NewsFeedItemAdapter(List<NewsFeedItem> items, Activity activity) {
        this.mItems = items;
        this.mActivity = activity;
    }

    @Override
    public NewsFeedItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news_feed, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsFeedItemAdapter.ViewHolder viewHolder, int i) {
        NewsFeedItem item = mItems.get(i);

        viewHolder.mDate.setText(item.getDate());
        viewHolder.mTitle.setText(item.getTitle());
        viewHolder.mMessage.setText(item.getMessage());

        UrlImageViewHelper.setUrlDrawable(viewHolder.mCover, item.getPhotoUrl());

        viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "open news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMessage;
        private TextView mTitle;
        private TextView mDate;
        private ImageView mCover;
        private View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;

            mMessage = (TextView) itemView.findViewById(R.id.message);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mCover = (ImageView) itemView.findViewById(R.id.cover);
        }
    }
}
