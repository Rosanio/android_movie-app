package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameStartActivity extends AppCompatActivity {
    @Bind(R.id.actorNameTextView) TextView mActorNameTextView;
    @Bind(R.id.actorImageView) ImageView mActorImageView;

    public ArrayList<Actor> mFamousActors = new ArrayList<>();
    public Actor actor;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        getActors();
    }

    private void getActors() {
        final MovieDBService movieDBService = new MovieDBService();

        movieDBService.findFamousActors(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mFamousActors = movieDBService.processActorResults(response);
                int randomNumber = random.nextInt(mFamousActors.size());
                actor = mFamousActors.get(randomNumber);
            }
        });

    }

}