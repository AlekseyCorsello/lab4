package com.example.lab3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonGameApi {
    @GET("api1/games")
    Call<List<GameEntity>> getGameInfo();
}
