package com.ramilforflatstack.response;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.ramilforflatstack.content.NewsFeedItem;
import com.ramilforflatstack.model.Attachment;
import com.ramilforflatstack.model.Autor;
import com.ramilforflatstack.model.FeedItem;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKAttachments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedResponseContent {

    private List<FeedItem> items;

    private List<Autor> profiles;

    private List<Autor> groups;

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
        for (FeedItem item : items) {
            item.toRightModel();
        }


        ActiveAndroid.beginTransaction();
        try {
            for (FeedItem item : items) {
                new Delete().from(FeedItem.class)
                        .where("PostId = ? AND Date = ?", item.getPostId(), item.getDate())
                        .execute();
                new Delete().from(Attachment.class)
                        .where("PostId = ? AND Autor_id = ?", item.getPostId(), item.getSourceId())
                        .execute();
            }

            for (Autor profile : profiles) {
                new Delete().from(Autor.class)
                        .where("Autor_id = ?", profile.getAutorId())
                        .execute();
            }

            for (Autor group : groups) {
                new Delete().from(Autor.class)
                        .where("Autor_id = ?", group.getAutorId())
                        .execute();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }


        ActiveAndroid.beginTransaction();
        try {
            for (FeedItem item : items) {
                item.save();
                for(Attachment attachment : item.getMyAttachments()) {
                    attachment.save();
                }
            }

            for (Autor profile : profiles) {
                profile.save();
            }

            for (Autor group : groups) {
                group.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
