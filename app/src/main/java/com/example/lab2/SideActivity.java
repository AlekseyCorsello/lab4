package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);

        String name = getIntent().getStringExtra("NAME");
        int image = getIntent().getIntExtra("IMAGE", 0);
        String films = getIntent().getStringExtra("FILMS");
        String shortFilms = getIntent().getStringExtra("SHORT_FILMS");
        String series = getIntent().getStringExtra("SERIES");
        String enemies = getIntent().getStringExtra("ENEMIES");
        String friends = getIntent().getStringExtra("FRIENDS");

        TextView nameTV = findViewById(R.id.charName);
        ImageView imageTV = findViewById(R.id.sideActImg);
        TextView filmsTV = findViewById(R.id.charFilms);
        TextView shortFilmsTV = findViewById(R.id.charShortFilms);
        TextView seriesTV = findViewById(R.id.charSeries);
        TextView enemiesTV = findViewById(R.id.charEnemies);
        TextView friendsTV = findViewById(R.id.charFriends);

        nameTV.setText(name);
        imageTV.setImageResource(image);
        filmsTV.setText(films);
        shortFilmsTV.setText(shortFilms);
        seriesTV.setText(series);
        enemiesTV.setText(enemies);
        friendsTV.setText(friends);
    }
}