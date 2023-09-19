package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MyInterface {
    String URL_BASE = "https://www.mmobomb.com/";
    ArrayList<RowModel> rowModels = new ArrayList<>();
    List<Game> gameInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = configureRecyclerView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonGameApi jsonGameApi = retrofit.create(JsonGameApi.class);

        Call<List<Game>> call = jsonGameApi.getGameInfo();

        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (!response.isSuccessful()) {
                    Log.d("onResponse().", "Code: " + response.code());
                    return;
                }

                gameInfos = response.body();
                setUpRowModels();
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.d("onFailure(): ", t.getMessage(), t);
            }
        });
    }

    private RecyclerView configureRecyclerView() {
        MyAdapter adapter = new MyAdapter(this, rowModels, this);
        RecyclerView recyclerView = findViewById(R.id.myRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    private void setUpRowModels() {
        for (Game game : gameInfos) {
            rowModels.add(new RowModel(
                    game.getThumbnail(),
                    game.getRowName(),
                    game.getGenre(),
                    game.getDescription(),
                    game.getDevStudio(),
                    game.getPublisher(),
                    game.getDate(),
                    game.getPlatform()));
        }
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(MainActivity.this, SideActivity.class);

        intent.putExtra("NAME", rowModels.get(pos).getRowName());
        intent.putExtra("THUMBNAIL", rowModels.get(pos).getThumbnail());
        intent.putExtra("GENRE", rowModels.get(pos).getGenre());
        intent.putExtra("DESCRIPTION", rowModels.get(pos).getDescription());
        intent.putExtra("DEV_STUDIO", rowModels.get(pos).getDevStudio());
        intent.putExtra("PUBLISHER", rowModels.get(pos).getPublisher());
        intent.putExtra("DATE", rowModels.get(pos).getDate());
        intent.putExtra("PLATFORMS", rowModels.get(pos).getPlatforms());

        startActivity(intent);
    }
}