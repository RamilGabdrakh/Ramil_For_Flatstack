package com.ramilforflatstack.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ramil-g on 14.06.15.
 */
@Table(name = "Autors")
public class Autor extends Model {

    @Column(name = "Autor_id")
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "PhotoUrl")
    @SerializedName("photo_100")
    private String photoUrl;

    public long getAutorId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public static Autor getById(long autorId) {
        autorId = Math.abs(autorId);
        Autor autor = new Select()
                .from(Autor.class)
                .where("Autor_id = ?", autorId)
                .executeSingle();
        if (autor == null) {
            autor = new Autor();
            autor.id = autorId;
            autor.name = "Тачки";
            autor.photoUrl = "http://cs11271.vk.me/v11271678/1214/T0mpZwUb4Ac.jpg";
        }
        return autor;
    }

    public void absId(){
        id = Math.abs(id);
    }
}
