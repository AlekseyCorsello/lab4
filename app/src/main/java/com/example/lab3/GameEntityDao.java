package com.example.lab3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GameEntity ent);

    @Delete
    void delete(GameEntity ent);

    @Query("SELECT * FROM games")
    List<GameEntity> getAll();
    @Query("SELECT * FROM games WHERE id = :id")
    GameEntity selectGameByDB_ID(int id);

    @Query("DELETE FROM games")
    void deleteAll();
}
