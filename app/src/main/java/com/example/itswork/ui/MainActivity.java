package com.example.itswork.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.itswork.MovieItemClickListener;
import com.example.itswork.R;
import com.example.itswork.models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import static android.Manifest.permission.READ_PHONE_STATE;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {
    private static final int PERMISSION_REQUEST = 123;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.movie_recycler_view);

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
        final MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter();

        ArrayList<Movies> stringArrayList = new ArrayList<>();
        stringArrayList.add(new Movies("Avenger", "2019", "lorem ipsum"));
        stringArrayList.add(new Movies("Kimi no Nawa", "2018", "lorem ipsum"));
        stringArrayList.add(new Movies("Forest Gump", "2016", "lorem ipsum"));

        adapter.setListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setDataList(stringArrayList);
        recyclerView.setAdapter(adapter);


        RequestQueue requestQueue = Volley.newRequestQueue(this);


        Log.i("DRG", "Context :" + (Context)this);
        String url ="https://api.themoviedb.org/3/discover/movie?api_key=113fd5030e4778ee7d003694bb439965&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("DRG","onResponse : " + response);
                ArrayList<Movies> moviesArrayList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    Log.i("DRG","title : " + jsonArray.toString());
                    for (int idx = 0; idx < jsonArray.length() ; idx++) {
                        JSONObject movieJSONObject = (JSONObject) jsonArray.get(idx);
                        moviesArrayList.add(new Movies(movieJSONObject));
                    }
//
                    adapter.setDataList(moviesArrayList);
                    adapter.notifyDataSetChanged();
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
    public void onItemClickListener(Movies movies) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra("movie_data", movies);
        startActivity(intent);
    }
}