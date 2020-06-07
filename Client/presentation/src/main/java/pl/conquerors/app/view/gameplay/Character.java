package pl.conquerors.app.view.gameplay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import pl.conquerors.app.view.gameplay.Map;

import static pl.conquerors.app.view.gameplay.Map.tileMap;
import static pl.conquerors.app.view.gameplay.Map.overlay;

public class Character implements Object {

    private Rect rectangle;
    private int color;

    public Character(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
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
        //overlay = tileMap;
        if (overlay[point.x][point.y] == 4) {
            clear_position();
            overlay[point.x][point.y] = 3;
        }
    }
}
