package com.ramilforflatstack.adapter;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramilforflatstack.R;
import com.ramilforflatstack.content.NewsFeedItem;
import com.ramilforflatstack.fragment.NewsFullFragment;
import com.ramilforflatstack.tools.CropSquareTransformation;
import com.ramilforflatstack.tools.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedItemAdapter extends RecyclerView.Adapter<NewsFeedItemAdapter.ViewHolder> {

    private List<NewsFeedItem> mItems;
    private AppCompatActivity mActivity;

    public NewsFeedItemAdapter(List<NewsFeedItem> items, AppCompatActivity activity) {
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
        final NewsFeedItem item = mItems.get(i);

        String date = DateUtils.getTextDate(item.getDate());

        viewHolder.mDate.setText(date);
        viewHolder.mTitle.setText(item.getTitle());
        viewHolder.mMessage.setText(item.getShortMessage());

        Picasso.with(mActivity)
                .load(item.getPhotoUrl())
                .transform(new CropSquareTransformation())
                .placeholder(R.drawable.placeholder)
                .into(viewHolder.mCover);

        viewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("Autor_id", item.getAutorId());
                bundle.putLong("PostId", item.getPostId());

                NewsFullFragment fragment = new NewsFullFragment();
                fragment.setArguments(bundle);

                mActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment, NewsFullFragment.class.getName())
                        .addToBackStack(NewsFullFragment.class.getName())
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public NewsFeedItem getLastItem() {
        return mItems.get(getItemCount() - 1);
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
