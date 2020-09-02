package com.example.itswork.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itswork.R;

public class DetailMovieActivity extends AppCompatActivity {
    private TextView movieTitleText;
    private TextView movieReleaseText;
    private ImageView moviePosterImage;
    private TextView movieDescText;

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

    }

    @Override
    protected void onDestroy() {
        Log.i("DRG","onDestroy :: " + this.getClass().getSimpleName());
        super.onDestroy();
    }
}