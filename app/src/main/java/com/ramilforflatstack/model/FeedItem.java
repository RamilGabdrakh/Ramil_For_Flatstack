package com.ramilforflatstack.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ramil-g on 14.06.15.
 */
@Table(name = "FeedItems")
public class FeedItem extends Model {

    @Column(name = "Autor_id")
    @SerializedName("source_id")
    private long sourceId;

    @Column(name = "PostId")
    @SerializedName("post_id")
    private long postId;

    @Column(name = "Date")
    private long date;

    @Column(name = "Text")
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

    public long getPostId() {
        return postId;
    }
}
