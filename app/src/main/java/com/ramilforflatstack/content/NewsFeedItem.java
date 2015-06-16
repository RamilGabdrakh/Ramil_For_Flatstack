package com.ramilforflatstack.content;

import com.activeandroid.query.Select;
import com.ramilforflatstack.model.Autor;
import com.ramilforflatstack.model.FeedItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedItem {
    private static final int MESSAGE_MAX_LENGTH = 20;

    private long postId;
    private long autorId;
    private String message;
    private String title;
    private String photoUrl;
    private long date;

    public NewsFeedItem(long postId, long autorId, String message, String title, String photoUrl, long date) {
        this.postId = postId;
        this.autorId = autorId;
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
        return shortMessage;
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

    public long getAutorId() {
        return autorId;
    }

    public static List<NewsFeedItem> getNewsFeedItem(long endTime, int limit) {
        List<NewsFeedItem> result = new ArrayList<>();

        List<FeedItem> feedItems = new Select()
                .from(FeedItem.class)
                .where("Date < ?", endTime)
                .orderBy("Date DESC")
                .limit(limit)
                .execute();

        for (FeedItem feedItem : feedItems) {
            Autor autor = Autor.getById(feedItem.getSourceId());

            NewsFeedItem item = new NewsFeedItem(feedItem.getPostId(), feedItem.getSourceId(),
                    feedItem.getText(), autor.getName(), autor.getPhotoUrl(), feedItem.getDate());

            result.add(item);
        }

        return result;
    }
}
