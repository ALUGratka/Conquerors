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
import pl.conquerors.app.domain.model.TreasureAchievement;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.model.mapper.TreasureAchievementMapper.transform;

public class Map extends View {
    private final int TILE_SIZE = 100;
    public static int[][] tileMap;
    public static int[][] overlay;
    private static int rows, columns;
    private Drawable box;
    Paint strokePaint, fillPaint;
    List<TreasureAchievement> treasureAchievements;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Map(Context context) {
        super(context);
        box = getResources().getDrawable(R.mipmap.skarb, null);
        box.mutate().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        fillPaint = new Paint();
        strokePaint = new Paint();
        create_map();

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
        getTreasureAchievement(1);
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
            }
        }
        for(TreasureAchievement treasureAchievement:treasureAchievements)
        {
            int x = Integer.parseInt(treasureAchievement.getmObjectPositionX());
            int y = Integer.parseInt(treasureAchievement.getmObjectPositionY());

            box.setBounds(x * TILE_SIZE, y * TILE_SIZE, x * TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE);
            box.draw(canvas);
            canvas.drawRect(x * TILE_SIZE, y * TILE_SIZE, x * TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE, strokePaint);
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
                treasureAchievements = transform(response.body());
            }
            @Override
            public void onFailure(Call<List<TreasureAchievementEntity>> call, Throwable throwable) {
                treasureAchievements = new ArrayList<TreasureAchievement>();
                Log.e(TAG, throwable.toString());
            }
        });
    }

}
