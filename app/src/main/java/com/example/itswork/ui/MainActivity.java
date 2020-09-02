package com.example.itswork.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.itswork.R;
import com.example.itswork.models.Movies;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static android.Manifest.permission.READ_PHONE_STATE;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST = 123;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.movie_recycler_view);
//        moviePosterImage = findViewById(R.id.movie_poster);
//        movieDescText = findViewById(R.id.movie_desc);
//        nextButton = findViewById(R.id.next_button);
        init();
    }

    @Override
    protected void onResume() {
        Log.i("DRG","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("DRG","onPause");
        super.onPause();
    }

    private void init() {

// ...
        MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter();

        ArrayList<Movies> stringArrayList = new ArrayList<>();
        stringArrayList.add(new Movies("Avenger", "2019", "lorem ipsum"));
        stringArrayList.add(new Movies("Kimi no Nawa", "2018", "lorem ipsum"));
        stringArrayList.add(new Movies("Forest Gump", "2016", "lorem ipsum"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setDataList(stringArrayList);
        recyclerView.setAdapter(adapter);



        RequestQueue requestQueue = Volley.newRequestQueue(this);

        /*nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DRG","Button clicked");
                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
                startActivity(intent);
            }
        });*/

        Log.i("DRG", "Context :" + (Context)this);
        String url ="https://api.themoviedb.org/3/movie/550?api_key=113fd5030e4778ee7d003694bb439965";
        final String imgBaseUrl = "https://image.tmdb.org/t/p/w500/";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("DRG","onResponse : " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.i("DRG","title : " + jsonObject.getString("title"));
//                    movieTitleText.setText(jsonObject.getString("title"));
//                    String releaseDate = jsonObject.getString("release_date");
//                    String releaseYear = releaseDate.split("[-]")[0];
//                    movieReleaseText.setText(releaseYear);
//                    Picasso.get().load(imgBaseUrl + jsonObject.getString("poster_path")).into(moviePosterImage);

//                    movieDescText.setText(jsonObject.getString("overview"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("DRG","error response : " + error.getMessage());
        }
        });

        requestQueue.add(stringRequest);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("DRG","onRequestPermissionsResult :: request code : " + requestCode
                + " permissions : " + Arrays.toString(permissions) + " \n grantResults : " + Arrays.toString(grantResults));
        for (String permission : permissions) {
            if(permission.equals(READ_PHONE_STATE)) {
                
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("DRG","onActivityResult :: request code : " + requestCode + " :: resultCode : " + resultCode );
    }
}