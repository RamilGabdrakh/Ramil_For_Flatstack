package com.ramilforflatstack.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ramil-g on 14.06.15.
 */
public class NewsFeedResponse {

    @SerializedName("response")
    public NewsFeedResponseContent content;
}
