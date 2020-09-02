package com.example.itswork.models;

public class Movies {

    private String title;
    private String releaseDate;
    private String desc;

    public Movies(String title, String releaseDate, String desc) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.desc = desc;
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
}
