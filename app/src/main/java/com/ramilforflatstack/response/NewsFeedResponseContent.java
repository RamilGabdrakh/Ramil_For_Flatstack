package com.ramilforflatstack.response;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.google.gson.annotations.SerializedName;
import com.ramilforflatstack.content.NewsFeedItem;
import com.ramilforflatstack.model.Autor;
import com.ramilforflatstack.model.FeedItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedResponseContent {

    private List<FeedItem> items;

    private List<Autor> profiles;

    private List<Autor> groups;

    @SerializedName("next_from")
    private String nextFrom;

    public List<NewsFeedItem> toNewsList() {
        List<NewsFeedItem> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {

            String message = items.get(i).getText();
            long ownerId = items.get(i).getSourceId();
            Autor autor = Autor.getById(ownerId);
            String title = autor.getName();
            String coverUrl = autor.getPhotoUrl();
            long date = items.get(i).getDate();
            long postId = items.get(i).getPostId();

            NewsFeedItem item = new NewsFeedItem(postId, message, title, coverUrl, date);
            result.add(item);
        }
        return result;
    }

    public void save() {
        ActiveAndroid.beginTransaction();
        try {
            for(FeedItem item : items) {
                new Delete().from(FeedItem.class)
                        .where("PostId = ? AND Date = ?", item.getPostId(), item.getDate())
                        .execute();
            }

            for(Autor profile : profiles) {
                new Delete().from(Autor.class)
                        .where("Autor_id = ?", profile.getAutorId())
                        .execute();
            }

            for(Autor group : groups) {
                new Delete().from(Autor.class)
                        .where("Autor_id = ?", group.getAutorId())
                        .execute();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }


        ActiveAndroid.beginTransaction();
        try {
            for(FeedItem item : items) {
               item.save();
            }

            for(Autor profile : profiles) {
                profile.save();
            }

            for(Autor group : groups) {
                group.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }
}
