package pl.conquerors.app.view.gameplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.EnemiesAchievement;
import pl.conquerors.app.domain.model.TreasureAchievement;
import pl.conquerors.app.model.EnemiesAchievementEntity;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import pl.conquerors.app.model.mapper.TreasureAchievementMapper;
import pl.conquerors.app.model.mapper.EnemiesAchievementEntityMapper;

public class Map extends View {
    private final int TILE_SIZE = 100;
    public static int[][] tileMap;
    public static int[][] overlay;
    private static int rows, columns;
    private Drawable box;
    Paint strokePaint, fillPaint;
    List<TreasureAchievement> treasureAchievements;
    List<EnemiesAchievement> enemiesAchievements;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Map(Context context) {
        super(context);
        box = getResources().getDrawable(R.mipmap.skarb, null);
        box.mutate().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        fillPaint = new Paint();
        strokePaint = new Paint();
        create_map();
        getTreasureAchievement(1);
        getEnemiesAchievement(1);
    }

    public void create_map() {
        tileMap = new int[30][20];
        rows = tileMap.length;
        columns = tileMap[1].length;
        Random randomValue = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int r = randomValue.nextInt(10);
                if (r % 10 != 1) {
                    tileMap[i][j] = 1;
                } else {
                    tileMap[i][j] = 0;
                }
            }
        }
        overlay = tileMap;
        overlay[2][15] = 3;
    }

    public void makeTreasures()
    {
        for(TreasureAchievement treasureAchievement:treasureAchievements)
        {
            int x = Integer.parseInt(treasureAchievement.getmObjectPositionX());
            int y = Integer.parseInt(treasureAchievement.getmObjectPositionY());
            int treasureId = treasureAchievement.getmTreasureId();
            overlay[x][y] = 10 + treasureId;
        }
    }

    public void makeEnemies()
    {
        for(EnemiesAchievement enemiesAchievement:enemiesAchievements)
        {
            int x = Integer.parseInt(enemiesAchievement.getmObjectPositionX());
            int y = Integer.parseInt(enemiesAchievement.getmObjectPositionY());
            int enemyId = enemiesAchievement.getmEnemyId();
            overlay[x][y] = 30 + enemyId;
        }
    }


    public void show_move() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (overlay[i][j] == 3) {

                    if (overlay[i + 1][j + 1] != 0)
                        if (!(overlay[i][j + 1] == 0 && overlay[i + 1][j] == 0))
                            overlay[i + 1][j + 1] = 4;

                    if (overlay[i + 1][j - 1] != 0)
                        if (!(overlay[i][j - 1] == 0 && overlay[i + 1][j] == 0))
                            overlay[i + 1][j - 1] = 4;

                    if (overlay[i - 1][j + 1] != 0)
                        if (!(overlay[i - 1][j] == 0 && overlay[i][j + 1] == 0))
                            overlay[i - 1][j + 1] = 4;

                    if (overlay[i - 1][j - 1] != 0)
                        if (!(overlay[i][j - 1] == 0 && overlay[i - 1][j] == 0))
                            overlay[i - 1][j - 1] = 4;

                    if (overlay[i][j + 1] != 0)
                        overlay[i][j + 1] = 4;
                    if (overlay[i][j - 1] != 0)
                        overlay[i][j - 1] = 4;

                    if (overlay[i + 1][j] != 0)
                        overlay[i + 1][j] = 4;
                    if (overlay[i - 1][j] != 0)
                        overlay[i - 1][j] = 4;

                }
            }
        }
    }

    public void showMap(Canvas canvas) {

        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(3);

        show_move();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int pos_i = TILE_SIZE * i;
                int pos_j = TILE_SIZE * j;

                if (overlay[i][j] == 1) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.GREEN);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] == 0) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.RED);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] == 4) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.YELLOW);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if(overlay[i][j] == 3){
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.WHITE);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                }

                else if(overlay[i][j] > 10 && overlay[i][j]<30){
                    box.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                    box.draw(canvas);
                    canvas.drawRect(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE, strokePaint);
                }

                else if(overlay[i][j] > 30){
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.BLUE);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                }
            }
        }
    }


    public void update() {

    }

    public void getTreasureAchievement(int gameId) {
        Call<List<TreasureAchievementEntity>> call = RestClient.getInstance().getTreasuresAchievement(gameId);

        call.enqueue(new Callback<List<TreasureAchievementEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TreasureAchievementEntity>> call, Response<List<TreasureAchievementEntity>> response) {
                treasureAchievements = TreasureAchievementMapper.transform(response.body());
                makeTreasures();
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
                makeEnemies();
            }
            @Override
            public void onFailure(Call<List<EnemiesAchievementEntity>> call, Throwable throwable) {
                enemiesAchievements = new ArrayList<>();
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
