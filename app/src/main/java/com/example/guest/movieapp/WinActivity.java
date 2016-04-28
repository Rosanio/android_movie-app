package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WinActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.playAgainButton) Button mPlayAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        ButterKnife.bind(this);

        mPlayAgainButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.playAgainButton:
                Intent intent = new Intent(this, GameStartActivity.class);
                startActivity(intent);
        }
    }
}
