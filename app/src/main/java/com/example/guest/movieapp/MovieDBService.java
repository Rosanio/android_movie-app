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

    public static void findMovies(String id, Callback callback) {
        String MOVIE_DB_KEY = Constants.MOVIE_DB_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIE_DB_BASE_URL).newBuilder();
        urlBuilder.addPathSegment("person");
        urlBuilder.addPathSegment(id);
        urlBuilder.addPathSegment("movie_credits");
        urlBuilder.addQueryParameter(Constants.MOVIE_DB_API_KEY_QUERY_PARAMETER, Constants.MOVIE_DB_KEY);
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


            if(response.isSuccessful()) {
                JSONObject movieDBJSON = new JSONObject(jsonData);
                JSONArray resultJSON = movieDBJSON.getJSONArray("cast");
                for(int i = 0; i < resultJSON.length(); i++) {
                    JSONObject movieJSON = resultJSON.getJSONObject(i);
                    String title = movieJSON.getString("title");
                    String movieId = movieJSON.getString("id");
                    String movieUrl = "https://image.tmdb.org/t/p/w1280" + movieJSON.getString("poster_path");
                    Movie movie = new Movie(title, movieUrl, movieId);
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

    public ArrayList<Actor> processActorResults(Response response) {
        ArrayList<Actor> actors = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            Log.d("actors", jsonData);
            if(response.isSuccessful()) {
                JSONObject movieDBJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = movieDBJSON.getJSONArray("results");
                for(int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject actorJSON = resultsJSON.getJSONObject(i);
                    String name = actorJSON.getString("name");
                    String id = actorJSON.getString("id");
                    String imageUrl = "http://image.tmdb.org/t/p/original/" + actorJSON.getString("profile_path");
                    Actor actor = new Actor(name, imageUrl, id);
                    actors.add(actor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actors;
    }
}
