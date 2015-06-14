package com.ramilforflatstack.response;

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

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(items.get(i).getDate() * 1000));

            Calendar yesterday = (Calendar) calendar.clone();
            yesterday.set(Calendar.HOUR_OF_DAY, 0);
            yesterday.set(Calendar.MINUTE, 0);
            yesterday.set(Calendar.SECOND, 0);

            String date;
            if(calendar.after(yesterday)) {
                date = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
            } else {
                date = calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR);
            }

            NewsFeedItem item = new NewsFeedItem(message, title, coverUrl, date);
            result.add(item);
        }
        return result;
    }

}
