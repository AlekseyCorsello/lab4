package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyInterface {

    ArrayList<RowModel> rowModels = new ArrayList<>();
    ArrayList<Description> charDescr = new ArrayList<>();

    int[] rowImages = {R.drawable.maui, R.drawable.ariel, R.drawable.elsa,
                       R.drawable.rapunzel, R.drawable.ursula, R.drawable.vader,
                       R.drawable.buzz, R.drawable.r2d2, R.drawable.woody, R.drawable.hansolo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDescription();
        setUpRowModels();

        MyAdapter adapter = new MyAdapter(this, rowModels, this);
        RecyclerView recyclerView = findViewById(R.id.myRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setUpDescription() {
        String[] films = getResources().getStringArray(R.array.films);
        String[] shortFilms = getResources().getStringArray(R.array.short_films);
        String[] series = getResources().getStringArray(R.array.series);
        String[] enemies = getResources().getStringArray(R.array.enemies);
        String[] friends = getResources().getStringArray(R.array.friends);


        for (int i = 0; i < films.length; i++) {
            charDescr.add(new Description(films[i], shortFilms[i],
                                          series[i], enemies[i], friends[i]));
        }
    }

    private void setUpRowModels() {
        String[] rowNames = getResources().getStringArray(R.array.disney_names_arr);

        for (int i = 0; i < rowNames.length; i++) {
            rowModels.add(new RowModel(rowImages[i], rowNames[i], charDescr.get(i)));
        }
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(MainActivity.this, SideActivity.class);

        intent.putExtra("NAME", rowModels.get(pos).getRowName());
        intent.putExtra("IMAGE", rowModels.get(pos).getImage());
        intent.putExtra("FILMS", rowModels.get(pos).getDescription().films);
        intent.putExtra("SHORT_FILMS", rowModels.get(pos).getDescription().shortFilms);
        intent.putExtra("SERIES", rowModels.get(pos).getDescription().series);
        intent.putExtra("ENEMIES", rowModels.get(pos).getDescription().enemies);
        intent.putExtra("FRIENDS", rowModels.get(pos).getDescription().friends);

        startActivity(intent);
    }
}