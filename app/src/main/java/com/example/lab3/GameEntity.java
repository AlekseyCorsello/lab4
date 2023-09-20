package com.example.lab3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "games")
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    public String rowName;
    @ColumnInfo(name = "thumbnail")
    public String thumbnail;
    @SerializedName("short_description")
    @ColumnInfo(name = "short_description")
    public String description;
    public String genre;
    public String platform;
    public String publisher;
    @SerializedName("developer")
    public String devStudio;
    @SerializedName("release_date")
    public String date;
}
