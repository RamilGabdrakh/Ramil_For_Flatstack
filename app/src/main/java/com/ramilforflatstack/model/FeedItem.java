package com.ramilforflatstack.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ramil-g on 14.06.15.
 */
public class FeedItem {

    @SerializedName("source_id")
    private long sourceId;

    private long date;

    private String text;

    public long getSourceId() {
        return sourceId;
    }

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
