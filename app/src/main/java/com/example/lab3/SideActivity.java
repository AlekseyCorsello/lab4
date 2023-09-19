package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);


        String name = getIntent().getStringExtra("NAME");
        String thumbnail = getIntent().getStringExtra("THUMBNAIL");
        String genre = getIntent().getStringExtra("GENRE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String devStudio = getIntent().getStringExtra("DEV_STUDIO");
        String publisher = getIntent().getStringExtra("PUBLISHER");
        String date = getIntent().getStringExtra("DATE");
        String platforms = getIntent().getStringExtra("PLATFORMS");


        TextView nameTV = findViewById(R.id.gameName);
        ImageView thumbnailIV = findViewById(R.id.thumbnail);
        TextView genreTV = findViewById(R.id.gameGenre);
        TextView descriptionTV = findViewById(R.id.gameDescription);
        TextView devStudioTV = findViewById(R.id.gameDevStudio);
        TextView publisherTV = findViewById(R.id.gamePublisher);
        TextView dateTV = findViewById(R.id.gameDate);
        TextView platformsTV = findViewById(R.id.gamePlatforms);


        Glide.with(this)
                .load(thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnailIV);

        nameTV.setText(name);
        genreTV.setText(genre);
        descriptionTV.setText(description);
        devStudioTV.setText(devStudio);
        publisherTV.setText(publisher);
        dateTV.setText(date);
        platformsTV.setText(platforms);
    }
}