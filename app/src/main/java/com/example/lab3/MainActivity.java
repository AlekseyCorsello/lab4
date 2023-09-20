package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = configureRecyclerView();

        GameRepository repos = new GameRepository(getApplication());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonGameApi jsonGameApi = retrofit.create(JsonGameApi.class);

        Call<List<GameEntity>> call = jsonGameApi.getGameInfo();

        call.enqueue(new Callback<List<GameEntity>>() {
            @Override
            public void onResponse(Call<List<GameEntity>> call, Response<List<GameEntity>> response) {
                if (!response.isSuccessful()) {
                    Log.d("onResponse().", "Code: " + response.code());
                    return;
                }

                List<GameEntity> gameEntities = response.body();
                Toast.makeText(MainActivity.this,
                        "Данные подгружены из API! "
                                + gameEntities.size(), Toast.LENGTH_SHORT).show();

                loadIntoDB(gameEntities, repos);
                setUpRowModels(repos);

                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GameEntity>> call, Throwable t) {
                Log.d("onFailure(): ", t.getMessage(), t);
                setUpRowModels(repos);
                recyclerView.getAdapter().notifyDataSetChanged();
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

    private void loadIntoDB(List<GameEntity> list, GameRepository repos) {
        repos.deleteAll();
        //insert into db
        for (GameEntity ent : list) {
            repos.insert(ent);
        }
    }

    private void setUpRowModels(GameRepository repos) {
        for (GameEntity game : repos.getAllEntities()) {
            rowModels.add(new RowModel(
                    game.id,
                    game.thumbnail,
                    game.rowName,
                    game.genre,
                    game.description,
                    game.devStudio,
                    game.publisher,
                    game.date,
                    game.platform));
        }
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(MainActivity.this, SideActivity.class);
        intent.putExtra("id", rowModels.get(pos).getId());
        startActivity(intent);
    }
}