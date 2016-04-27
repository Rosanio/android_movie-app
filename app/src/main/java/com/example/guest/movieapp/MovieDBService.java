package com.example.guest.movieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        urlBuilder.addPathSegment("movie");
        urlBuilder.addQueryParameter(Constants.MOVIE_DB_API_KEY_QUERY_PARAMETER, Constants.MOVIE_DB_KEY);
        urlBuilder.addQueryParameter("query", movie);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static void findFamousActors(Callback callback) {
        String MOVIE_DB_KEY = Constants.MOVIE_DB_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_DB_BASE_URL).newBuilder();
        urlBuilder.addPathSegment("person");
        urlBuilder.addPathSegment("popular");
        urlBuilder.addQueryParameter(Constants.MOVIE_DB_API_KEY_QUERY_PARAMETER, Constants.MOVIE_DB_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


    public ArrayList<Movie> processMovieResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            Log.d("data", jsonData);
            if(response.isSuccessful()) {
                JSONObject movieDBJSON = new JSONObject(jsonData);
                JSONArray resultJSON = movieDBJSON.getJSONArray("results");
                for(int i = 0; i < resultJSON.length(); i++) {
                    JSONObject movieJSON = resultJSON.getJSONObject(i);
                    String title = movieJSON.getString("title").toString();
                    Movie movie = new Movie(title);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    //todo: create processActors method
}
