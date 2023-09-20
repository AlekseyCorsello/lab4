package com.example.lab3;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class GameRepository {
    private GameEntityDao gameEntityDao;
    private List<GameEntity> allEntities;

    GameRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        gameEntityDao = db.gameEntityDao();
        allEntities = getAllEntities();
    }

    void insert(GameEntity ent) {
        new InsertGameEntityAsyncTask(gameEntityDao).execute(ent);
    }

    GameEntity selectGameByDB_ID(int id) {
        return selectByIDGameEntityAsyncTask(id);
    }

    void delete(GameEntity ent) {
        new DeleteGameEntityAsyncTask(gameEntityDao).execute(ent);
    }

    void deleteAll() {
        new DeleteAllGameEntityAsyncTask(gameEntityDao).execute();
    }

    private static class InsertGameEntityAsyncTask extends AsyncTask<GameEntity, Void, Void> {
        private GameEntityDao gameEntityDao;

        private InsertGameEntityAsyncTask(GameEntityDao gameEntityDao) {
            this.gameEntityDao = gameEntityDao;
        }

        @Override
        protected Void doInBackground(GameEntity... ents) {
            gameEntityDao.insert(ents[0]);

            return null;
        }
    }

    private static class DeleteGameEntityAsyncTask extends AsyncTask<GameEntity, Void, Void> {
        private GameEntityDao gameEntityDao;

        private DeleteGameEntityAsyncTask(GameEntityDao gameEntityDao) {
            this.gameEntityDao = gameEntityDao;
        }

        @Override
        protected Void doInBackground(GameEntity... ents) {
            gameEntityDao.delete(ents[0]);

            return null;
        }
    }

    private static class DeleteAllGameEntityAsyncTask extends AsyncTask<Void, Void, Void> {
        private GameEntityDao gameEntityDao;

        private DeleteAllGameEntityAsyncTask(GameEntityDao gameEntityDao) {
            this.gameEntityDao = gameEntityDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gameEntityDao.deleteAll();

            return null;
        }
    }

    List<GameEntity> getAllEntities() {
        ExecutorService myExec = Executors.newSingleThreadExecutor();

        Callable<List<GameEntity>> callable = () -> {
            return gameEntityDao.getAll();
        };

        Future<List<GameEntity>> future = myExec.submit(callable);
        List<GameEntity> tmp = null;
        try {
            tmp = future.get();
        } catch (ExecutionException | InterruptedException e) {}
        return tmp;
    }

    GameEntity selectByIDGameEntityAsyncTask(int id) {
        ExecutorService myExec = Executors.newSingleThreadExecutor();

        Callable<GameEntity> callable = () -> {
            return gameEntityDao.selectGameByDB_ID(id);
        };

        Future<GameEntity> future = myExec.submit(callable);
        GameEntity tmp = null;
        try {
            tmp = future.get();
        } catch (ExecutionException | InterruptedException e) {}
        return tmp;
    }
}