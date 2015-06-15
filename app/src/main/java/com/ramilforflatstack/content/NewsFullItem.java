package com.ramilforflatstack.content;

import com.activeandroid.query.Select;
import com.ramilforflatstack.model.Attachment;
import com.ramilforflatstack.model.Autor;
import com.ramilforflatstack.model.FeedItem;

import java.util.List;

/**
 * Created by Ramil on 15.06.2015.
 */
public class NewsFullItem extends NewsFeedItem {

    private List<Attachment> attachments;
    private long likeCount;

    public NewsFullItem(long postId, long autorId, String message, String title, String photoUrl, long date, long likeCount, List<Attachment> attachments) {
        super(postId, autorId, message, title, photoUrl, date);
        this.attachments = attachments;
        this.likeCount = likeCount;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public static NewsFullItem generateFullNewsItem(long autorId, long postId) {

        autorId = Math.abs(autorId);

        FeedItem item = new Select()
                .from(FeedItem.class)
                .where("Autor_id = ? AND PostId = ?", autorId, postId)
                .executeSingle();
        item.setMyAttachments(new Select()
                .from(Attachment.class)
                .where("Autor_id = ? AND PostId = ?", autorId, postId)
                .<Attachment>execute());


        Autor autor = Autor.getById(autorId);

        NewsFullItem result = new NewsFullItem(postId, autorId, item.getText(),
                autor.getName(), autor.getPhotoUrl(), item.getDate(),
                item.getLikeCount(), item.getMyAttachments());

        return result;
    }
}
