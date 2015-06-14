package com.ramilforflatstack.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ramil-g on 14.06.15.
 */
public class Autor {

    private long id;

    private String name;

    @SerializedName("photo_100")
    private String photoUrl;

    protected Autor(long id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public static Autor getById(long ownerId) {
        //TODO: get from DB;
        return new Autor(ownerId, "Тачки", "http://cs11271.vk.me/v11271678/1214/T0mpZwUb4Ac.jpg");
    }
}
