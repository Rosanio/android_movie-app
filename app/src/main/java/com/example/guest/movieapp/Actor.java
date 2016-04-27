package com.example.guest.movieapp;

import org.parceler.Parcel;

/**
 * Created by Guest on 4/27/16.
 */
@Parcel
public class Actor {
    private String mName;
    private String mImageUrl;
    private String mActorId;

    public Actor() {}

    public Actor(String name, String imageUrl, String actorId) {
        this.mImageUrl = imageUrl;
        this.mName = name;
        this.mActorId = actorId;
    }

    public String getName() {
        return mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getActorId() {
        return mActorId;
    }

}
