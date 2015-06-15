package com.ramilforflatstack.content;

import com.ramilforflatstack.model.Attachment;

import java.util.List;

/**
 * Created by Ramil on 15.06.2015.
 */
public class NewsFullItem extends NewsFeedItem {

    private List<Attachment> attachments;
    private long likeCount;

    public NewsFullItem(long postId, String message, String title, String photoUrl, long date, long likeCount, List<Attachment> attachments) {
        super(postId, message, title, photoUrl, date);
        this.attachments = attachments;
        this.likeCount = likeCount;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public long getLikeCount() {
        return likeCount;
    }
}
