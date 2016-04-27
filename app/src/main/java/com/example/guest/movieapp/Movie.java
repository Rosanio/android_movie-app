package com.example.guest.movieapp;

/**
 * Created by Guest on 4/27/16.
 */
public class Movie {
    private String mTitle;
    private String mPosterUrl;
    private String mMovieId;

    public Movie(String title, String posterUrl, String movieId) {
        mTitle = title;
        mPosterUrl = posterUrl;
        mMovieId = movieId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterUrl() { return mPosterUrl; }

    public String getMovieId() { return mMovieId; }
}
