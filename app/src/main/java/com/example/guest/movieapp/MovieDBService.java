package com.example.guest.movieapp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 4/27/16.
 */
public class MovieDBService {

    public static void findMovies(String movie, Callback callback) {
        String MOVIE_DB_KEY = Constants.MOVIE_DB_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_DB_BASE_URL).newBuilder();
        urlBuilder.addPathSegment("search");
        urlBuilder.addPathSegment(movie);
        urlBuilder.addQueryParameter(Constants.MOVIE_DB_API_KEY_QUERY_PARAMETER, Constants.MOVIE_DB_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
