package com.example.stackoverflowusersapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rahul.raj on 07/09/2016.
 */
public class User {

    @SerializedName("display_name")
    String name;

    @SerializedName("profile_image")
    String photo;

    @SerializedName("reputation")
    String reputation;

    public User(String name, String photo, String reputation) {
        this.name = name;
        this.photo = photo;
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", reputation='" + reputation + '\'' +
                '}';
    }
}
