package com.ramilforflatstack.content;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedItem {
    private static final int MESSAGE_MAX_LENGTH = 20;

    private long postId;
    private String message;
    private String title;
    private String photoUrl;
    private long date;

    public NewsFeedItem(long postId, String message, String title, String photoUrl, long date) {
        this.postId = postId;
        this.message = message;
        this.title = title;
        this.photoUrl = photoUrl;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getShortMessage() {
        String shortMessage = getMessage();
        if (shortMessage.length() > MESSAGE_MAX_LENGTH) {
            shortMessage = shortMessage.substring(0, MESSAGE_MAX_LENGTH) + "...";
        }
        return  shortMessage;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public long getDate() {
        return date;
    }

    public long getPostId() {
        return postId;
    }
}
