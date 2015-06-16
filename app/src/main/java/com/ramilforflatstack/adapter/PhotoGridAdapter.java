package com.ramilforflatstack.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.ramilforflatstack.R;
import com.ramilforflatstack.model.Attachment;
import com.ramilforflatstack.tools.CropSquareTransformation;
import com.ramilforflatstack.tools.OttoBus;
import com.ramilforflatstack.tools.events.PhotoLoadet;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramil-g on 08.03.15.
 */
public class PhotoGridAdapter extends BaseAdapter {

    private List<Attachment> mAttachments;
    private List<Boolean> mCreated;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public PhotoGridAdapter(Activity activity, List<Attachment> records) {
        mActivity = activity;
        this.mAttachments = records;
        mCreated = new ArrayList<>(mAttachments.size());
        mInflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAttachments.size();
    }

    @Override
    public Object getItem(int position) {
        return mAttachments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.item_photo_grid, null);
        ViewHolder holder = new ViewHolder(rowView);

        if (mAttachments.size() > position) {
            Attachment attachment = mAttachments.get(position);

            while (mCreated.size() <= position) {
                mCreated.add(false);
            }

            //if (!mCreated.get(position)) {
            mCreated.set(position, true);
            Picasso.with(mActivity)
                    .load(attachment.getPhotoUrl())
                    .transform(new CropSquareTransformation())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.mPhoto, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.e("mytag", "onSuccess");

                            PhotoLoadet event = new PhotoLoadet();
                            OttoBus.get().post(event);
                        }

                        @Override
                        public void onError() {

                        }
                    });
            //}

        }

        return rowView;
    }

    public void setGridViewHeightBasedOnChildren(GridView gridView, int itemsInRow, int spacing) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += itemsInRow) {
            int maxRowHeight = 0;
            for (int j = i; j < i + itemsInRow; j++) {
                View item = mInflater.inflate(R.layout.item_photo_grid, null);
                if (item != null) {
                    item.measure(0, 0);
                    maxRowHeight = Math.max(maxRowHeight, item.getMeasuredHeight());
                }
            }
            totalHeight += maxRowHeight + spacing;
        }

        totalHeight += 10;

        Log.e("mytag", "totalHeight = " + totalHeight);
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
        gridView.requestLayout();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}

