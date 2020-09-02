package com.example.itswork.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Movies implements Parcelable {
    private final String imgBaseUrl = "https://image.tmdb.org/t/p/w500/";
    private String title;
    private String releaseDate;
    private String posterUrl;
    private String desc;

    public Movies(String title, String releaseDate, String desc) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.desc = desc;
    }

    public Movies(JSONObject movieJSONObject) {
        try {
            this.title = movieJSONObject.getString("title");
            this.releaseDate = movieJSONObject.getString("release_date");
            this.posterUrl = imgBaseUrl + movieJSONObject.getString("poster_path");
            this.desc = movieJSONObject.getString("overview");
        } catch (JSONException e) {
            Log.w("DRG","JSONException :: " + e.getMessage());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDesc() {
        return desc;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterUrl);
        dest.writeString(this.desc);
    }

    protected Movies(Parcel in) {
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.posterUrl = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
