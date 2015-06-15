package com.ramilforflatstack.model;

import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;
import com.ramilforflatstack.content.NewsFeedItem;
import com.ramilforflatstack.content.NewsFullItem;
import com.ramilforflatstack.response.AttachmentResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKAttachments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramil-g on 14.06.15.
 */
@Table(name = "FeedItems")
public class FeedItem extends Model implements Serializable{

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

    @Column(name = "LikeCount")
    private long likeCount;

    private Like likes;

    private List<AttachmentResponse> attachments;

    private List<Attachment> myAttachments;

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

    public long getLikeCount() {
        return likeCount;
    }

    public List<Attachment> getMyAttachments() {
        return myAttachments;
    }

    public void setMyAttachments(List<Attachment> myAttachments) {
        this.myAttachments = myAttachments;
    }

    public void toRightModel(){
        sourceId = Math.abs(sourceId);
        myAttachments = toAttachmentsModel(attachments, postId, sourceId);
        likeCount = likes.count;
    }

    private List<Attachment> toAttachmentsModel(List<AttachmentResponse> vkApiAttachments, long postId, long sourceId) {
        List<Attachment> result = new ArrayList<>();
        if (vkApiAttachments != null) {
            for(AttachmentResponse attachment : vkApiAttachments) {
                if (TextUtils.equals(attachment.getType(), VKAttachments.TYPE_PHOTO) ) {
                    VKApiPhoto photo = attachment.getPhoto();

                    Attachment myAttachment = new Attachment();
                    myAttachment.setPhotoUrl(photo.photo_604);
                    myAttachment.setPostId(postId);
                    myAttachment.setSourceId(sourceId);
                    result.add(myAttachment);
                }
            }
        }

        return result;
    }

    private class Like {
        long count;
    }
}
