package com.example.stackoverflowusersapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rahul.raj on 07/09/2016.
 */
public class UserResponse {

    @SerializedName("items")
    List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
