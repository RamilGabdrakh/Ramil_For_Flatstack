package com.ramilforflatstack.response;

import com.vk.sdk.api.model.VKApiPhoto;

/**
 * Created by Ramil on 15.06.2015.
 */
public class AttachmentResponse {

    private String type;
    private VKApiPhoto photo;

    public String getType() {
        return type;
    }

    public VKApiPhoto getPhoto() {
        return photo;
    }
}
