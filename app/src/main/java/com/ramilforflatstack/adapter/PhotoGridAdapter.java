package com.ramilforflatstack.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.ramilforflatstack.R;
import com.ramilforflatstack.model.Attachment;

import java.util.List;

/**
 * Created by ramil-g on 08.03.15.
 */
public class PhotoGridAdapter extends BaseAdapter{

    private List<Attachment> attachments;

    private Activity mActivity;
    private LayoutInflater inflater;

    public PhotoGridAdapter(Activity activity, List<Attachment> records) {
        mActivity = activity;
        this.attachments = records;
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return attachments.size();
    }

    @Override
    public Object getItem(int position) {
        return attachments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.item_photo_grid, null);
        ViewHolder holder = new ViewHolder(rowView);
        final Attachment attachment = attachments.get(position);

        UrlImageViewHelper.setUrlDrawable(holder.mPhoto, attachment.getPhotoUrl());

        return rowView;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}

