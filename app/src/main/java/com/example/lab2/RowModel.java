package com.example.lab2;

public class RowModel {
    int image;
    String rowName;
    Description description;

    public RowModel(int image, String rowName, Description description) {
        this.image = image;
        this.rowName = rowName;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getRowName() {
        return rowName;
    }

    public Description getDescription() {
        return description;
    }
}
