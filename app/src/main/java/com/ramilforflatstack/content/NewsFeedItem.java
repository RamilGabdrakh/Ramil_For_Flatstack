package com.ramilforflatstack.content;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedItem {

    private String message;
    private String title;
    private String PhotoUrl;
    private String date;

    public NewsFeedItem(String message, String title, String photoUrl, String date) {
        this.message = message;
        this.title = title;
        PhotoUrl = photoUrl;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public String getDate() {
        return date;
    }
}
