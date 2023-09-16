package com.example.lab2;

public class Description {
    public String films;
    public String shortFilms;
    public String series;
    public String enemies;
    public String friends;

    public Description(String films, String shortFilms, String series, String enemies, String friends) {
        this.films = films;
        this.shortFilms = shortFilms;
        this.series = series;
        this.enemies = enemies;
        this.friends = friends;
    }

    public String getFilms() {
        return films;
    }

    public String getShortFilms() {
        return shortFilms;
    }

    public String getSeries() {
        return series;
    }

    public String getEnemies() {
        return enemies;
    }

    public String getFriends() {
        return friends;
    }
}
