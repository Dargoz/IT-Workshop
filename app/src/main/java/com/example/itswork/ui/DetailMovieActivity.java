package com.example.itswork.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itswork.R;
import com.example.itswork.models.Movies;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {
    private TextView movieTitleText;
    private TextView movieReleaseText;
    private ImageView moviePosterImage;
    private TextView movieDescText;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        movieTitleText = findViewById(R.id.movie_title);
        movieReleaseText = findViewById(R.id.movie_release_year);
        moviePosterImage = findViewById(R.id.movie_poster);
        movieDescText = findViewById(R.id.movie_desc);
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        init();

    }

    private void init() {
        Intent intent = getIntent();
        Movies movies = intent.getParcelableExtra("movie_data");
        movieTitleText.setText(movies.getTitle());
        String releaseDate = movies.getReleaseDate();
        String releaseYear = releaseDate.split("[-]")[0];
        movieReleaseText.setText(releaseYear);
        Picasso.get().load(movies.getPosterUrl()).into(moviePosterImage);
        movieDescText.setText(movies.getDesc());
    }

    @Override
    protected void onDestroy() {
        Log.i("DRG","onDestroy :: " + this.getClass().getSimpleName());
        super.onDestroy();
    }
}