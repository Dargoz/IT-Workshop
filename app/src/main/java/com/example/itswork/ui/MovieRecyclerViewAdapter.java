package com.example.itswork.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itswork.MovieItemClickListener;
import com.example.itswork.R;
import com.example.itswork.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieItemViewHolder> {
    private List<Movies> dataList = new ArrayList<>();

    private MovieItemClickListener listener;

    public void setDataList(List<Movies> dataList) {
        this.dataList = dataList;
    }

    public void setListener(MovieItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        holder.bindData(position);
        Log.i("DRG", "onBindView :: " + position);
    }

    @Override
    public int getItemCount() {
        Log.i("DRG","dataList : " + dataList.size());
        return dataList.size();
}

    class MovieItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        TextView movieTitleText;
        TextView movieDescText;


        public MovieItemViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.movie_container);
            movieTitleText = itemView.findViewById(R.id.movie_title);
            movieDescText = itemView.findViewById(R.id.description);
        }

        public void bindData(final int position) {
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClickListener(dataList.get(position));
                    }
                }
            });
            movieTitleText.setText(dataList.get(position).getTitle());
            movieDescText.setText(dataList.get(position).getDesc());
        }
    }
}
