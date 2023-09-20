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

        GameRepository repos = new GameRepository(getApplication());

        int id = getIntent().getIntExtra("id", 0);

        GameEntity ent = repos.selectGameByDB_ID(id);


        TextView nameTV = findViewById(R.id.gameName);
        ImageView thumbnailIV = findViewById(R.id.thumbnail);
        TextView genreTV = findViewById(R.id.gameGenre);
        TextView descriptionTV = findViewById(R.id.gameDescription);
        TextView devStudioTV = findViewById(R.id.gameDevStudio);
        TextView publisherTV = findViewById(R.id.gamePublisher);
        TextView dateTV = findViewById(R.id.gameDate);
        TextView platformsTV = findViewById(R.id.gamePlatforms);


        Glide.with(this)
                .load(ent.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnailIV);

        nameTV.setText(ent.rowName);
        genreTV.setText(ent.genre);
        descriptionTV.setText(ent.description);
        devStudioTV.setText(ent.devStudio);
        publisherTV.setText(ent.publisher);
        dateTV.setText(ent.date);
        platformsTV.setText(ent.platform);
    }
}