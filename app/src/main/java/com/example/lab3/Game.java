package com.example.lab3;

import com.google.gson.annotations.SerializedName;

public class Game {
    @SerializedName("title")
    private String rowName;
    private String thumbnail;
    @SerializedName("short_description")
    private String description;
    private String genre;
    private String platform;
    private String publisher;
    @SerializedName("developer")
    private String devStudio;
    @SerializedName("release_date")
    private String date;

    public String getRowName() {
        return rowName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDevStudio() {
        return devStudio;
    }

    public String getDate() {
        return date;
    }
}
