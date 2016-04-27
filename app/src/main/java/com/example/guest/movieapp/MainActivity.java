package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.movieTitleEditText) TextView mMovieTitleEditText;
    @Bind(R.id.submitSearchButton) Button mSubmitSearchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitSearchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.submitSearchButton:
                String title = mMovieTitleEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);

                intent.putExtra("title", title);
                startActivity(intent);
                break;
        }
    }
}
