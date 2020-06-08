package pl.conquerors.app.view.gameplay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.EnemiesAchievement;
import pl.conquerors.app.domain.model.Enemy;
import pl.conquerors.app.domain.model.Treasure;
import pl.conquerors.app.domain.model.TreasureAchievement;
import pl.conquerors.app.model.EnemiesAchievementEntity;
import pl.conquerors.app.model.EnemyEntity;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.model.TreasureEntity;
import pl.conquerors.app.model.mapper.EnemiesAchievementEntityMapper;
import pl.conquerors.app.model.mapper.EnemyEntityMapper;
import pl.conquerors.app.model.mapper.TreasureAchievementMapper;
import pl.conquerors.app.model.mapper.TreasureEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.view.gameplay.Map.overlay;

public class Character implements Object {

    private Rect rectangle;
    private int color;
    List<Enemy> enemies;
    List<Treasure> treasures;
    List<TreasureAchievement> treasureAchievements;
    List<EnemiesAchievement> enemiesAchievements;

    public Character(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
        getEnemies();
        getTreasures();
        getEnemiesAchievement(1);
        getTreasureAchievement(1);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        //overlay[2][15] = 3;
    }

    @Override
    public void update() {

    }

    public int[] getTile(int x, int y) {
        int[] xy = new int[2];
        xy[0] = (x / 100);
        xy[1] = (y / 100);
        return xy;
    }

    public void clear_position() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                if (overlay[i][j] == 3 || overlay[i][j] == 4) {
                    overlay[i][j] = 1;
                }
            }
        }
    }

    public void update(Point point) {
        int[] xy = new int[2];

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                if (overlay[i][j] == 3) {
                    xy[0] = i;
                    xy[1] = j;
                }
            }
        }

        int dis_x = Math.abs(xy[0] - point.x);
        int dis_y = Math.abs(xy[1] - point.y);

        int distance = dis_x + dis_y;

        if (overlay[point.x][point.y] == 4) {
            clear_position();
            overlay[point.x][point.y] = 3;
        }
        //dla skrzynek
        else if (overlay[point.x][point.y] > 5 && overlay[point.x][point.y] < 30 && distance == 1) {
            int treasure_id = overlay[point.x][point.y] - 10;
            get_treasure();

        }
        //dla wrogów
        else if (overlay[point.x][point.y] > 30 && distance == 1) {
            int enemy_id = overlay[point.x][point.y] - 30;
            begin_fight();
        }

    }

    public void get_treasure() {

    }

    public void begin_fight() {

    }

    public void getEnemies() {
        Call<List<EnemyEntity>> call = RestClient.getInstance().getEnemyEntity();

        call.enqueue(new Callback<List<EnemyEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<EnemyEntity>> call, Response<List<EnemyEntity>> response) {
                enemies = EnemyEntityMapper.transform(response.body());
            }

            @Override
            public void onFailure(Call<List<EnemyEntity>> call, Throwable t) {
                enemies = new ArrayList<>();
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getTreasures() {
        Call<List<TreasureEntity>> call = RestClient.getInstance().getTreasureEntity();

        call.enqueue(new Callback<List<TreasureEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TreasureEntity>> call, Response<List<TreasureEntity>> response) {
                treasures = TreasureEntityMapper.transform(response.body());
            }

            @Override
            public void onFailure(Call<List<TreasureEntity>> call, Throwable t) {
                treasures = new ArrayList<>();
                Log.e(TAG, t.toString());
            }
        });
    }

    public void getTreasureAchievement(int gameId) {
        Call<List<TreasureAchievementEntity>> call = RestClient.getInstance().getTreasuresAchievement(gameId);

        call.enqueue(new Callback<List<TreasureAchievementEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TreasureAchievementEntity>> call, Response<List<TreasureAchievementEntity>> response) {
                treasureAchievements = TreasureAchievementMapper.transform(response.body());
            }

            @Override
            public void onFailure(Call<List<TreasureAchievementEntity>> call, Throwable throwable) {
                treasureAchievements = new ArrayList<>();
                Log.e(TAG, throwable.toString());
            }
        });
    }

    public void getEnemiesAchievement(int gameId) {
        Call<List<EnemiesAchievementEntity>> call = RestClient.getInstance().getEnemiesAchievement(gameId);

        call.enqueue(new Callback<List<EnemiesAchievementEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<EnemiesAchievementEntity>> call, Response<List<EnemiesAchievementEntity>> response) {
                enemiesAchievements = EnemiesAchievementEntityMapper.transform(response.body());
            }

            @Override
            public void onFailure(Call<List<EnemiesAchievementEntity>> call, Throwable throwable) {
                enemiesAchievements = new ArrayList<>();
                Log.e(TAG, throwable.toString());
            }
        });
    }

}
