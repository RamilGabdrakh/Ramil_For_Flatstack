package com.ramilforflatstack.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramil on 15.06.2015.
 */

@Table(name = "Attachments")
public class Attachment extends Model {

    @Column(name = "PostId")
    private long postId;

    @Column(name = "Autor_id")
    private long sourceId;

    @Column(name = "PhotoUrl")
    private String photoUrl;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
