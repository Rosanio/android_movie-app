package com.example.guest.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GameStartActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.actorTextView) TextView mActorNameTextView;
    @Bind(R.id.actorImageView) ImageView mActorImageView;
    @Bind(R.id.moviesButton) Button mMoviesButton;
    int score = 0;
    String degrees = "";
    boolean callbackComplete = false;

    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    public ArrayList<Actor> mFamousActors = new ArrayList<>();
    public Actor actor;
    Random random = new Random();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        ButterKnife.bind(this);
        mContext = this;

        getActors();

        mMoviesButton.setOnClickListener(this);
    }

    private void getActors() {
        final MovieDBService movieDBService = new MovieDBService();

        movieDBService.findFamousActors(0, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mFamousActors = movieDBService.processActorResults(response, "results", mFamousActors);
                movieDBService.findFamousActors(1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mFamousActors = movieDBService.processActorResults(response, "results", mFamousActors);

                        callbackComplete = true;

                        GameStartActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int randomNumber = random.nextInt(mFamousActors.size());
                                actor = mFamousActors.get(randomNumber);
                                mActorNameTextView.setText(actor.getName());
                                degrees += actor.getName() + " was in ";
                                Picasso.with(mContext)
                                        .load(actor.getImageUrl())
                                        .resize(MAX_WIDTH, MAX_HEIGHT)
                                        .centerCrop()
                                        .into(mActorImageView);
                            }
                        });
                    }
                });

                Log.d("ACTORS", mFamousActors.toString());

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.moviesButton:
                if (callbackComplete) {
                    Intent intent = new Intent(GameStartActivity.this, MovieListActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("actor", Parcels.wrap(actor));
                    intent.putExtra("degrees", degrees);
                    startActivity(intent);
                } else {
                    Toast.makeText(GameStartActivity.this, "loading...", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}