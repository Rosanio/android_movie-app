package com.example.guest.movieapp;

/**
 * Created by Guest on 4/27/16.
 */
public class Actor {
    private String mName;
    private String mImageUrl;


    public Actor(String name, String imageUrl) {
        this.mImageUrl = imageUrl;
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

}
