package com.example.lab3;

public class RowModel {
    private int id;
    private String thumbnail;
    private String rowName;
    private String genre;
    private String description;
    private String devStudio;
    private String publisher;
    private String date;
    private String platforms;

    public RowModel(int id, String thumbnail, String rowName, String genre, String description, String devStudio, String publisher, String date, String platforms) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.rowName = rowName;
        this.genre = genre;
        this.description = description;
        this.devStudio = devStudio;
        this.publisher = publisher;
        this.date = date;
        this.platforms = platforms;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getRowName() {
        return rowName;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getDevStudio() {
        return devStudio;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public String getPlatforms() {
        return platforms;
    }

    public int getId() {
        return id;
    }
}
