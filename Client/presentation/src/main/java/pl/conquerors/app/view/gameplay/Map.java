package pl.conquerors.app.view.gameplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import java.util.Random;

import pl.conquerors.app.R;

public class Map extends View {
    private final int TILE_SIZE = 100;
    public static int[][] tileMap;
    public static int[][] overlay;
    private static int rows, columns;
    private Drawable box;
    Paint strokePaint, fillPaint;

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

        int x = 5;
        int y = 7;
        box.setBounds(x * TILE_SIZE, y * TILE_SIZE, x * TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE);
        box.draw(canvas);
        canvas.drawRect(x * TILE_SIZE, y * TILE_SIZE, x * TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE, strokePaint);

        x = 9;
        y = 1;
        box.setBounds(x * TILE_SIZE, y * TILE_SIZE, x * TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE);
        box.draw(canvas);
        canvas.drawRect(x * TILE_SIZE, y * TILE_SIZE, x * TILE_SIZE + TILE_SIZE, y * TILE_SIZE + TILE_SIZE, strokePaint);

    }


    public void update() {

    }

}
