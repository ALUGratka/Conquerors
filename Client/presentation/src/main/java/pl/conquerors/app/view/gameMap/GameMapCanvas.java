package pl.conquerors.app.view.gameMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import pl.conquerors.app.R;

public class GameMapCanvas extends View {

    private final int TILE_SIZE = 100;
    private static int[][] tileMap;
    private static int rows, columns;
    private Drawable box;
    Paint p;
    Random random;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameMapCanvas(Context context) {
        super(context);
        box = getResources().getDrawable(R.mipmap.skarb, null);
        box.mutate().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        p = new Paint();
        random = new Random();
        createTilemap();
    }

    public void createTilemap(){
        tileMap = new int[30][20];
        rows = tileMap.length;
        columns = tileMap[1].length;
        Random r = new Random();

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                tileMap[i][j] = r.nextInt(2);
            }
        }
    }

    public GameMapCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){

                int pos_i = TILE_SIZE * i;
                int pos_j = TILE_SIZE * j;

                int r  = random.nextInt(10);
                if(r%10!=1 && r%10!=2) {
                    p.setColor(Color.GREEN);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, p);
                }
                else if(r%10==2) {
                    p.setColor(Color.RED);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, p);
                }
                else {
                    box.setBounds(pos_i, pos_j, pos_i + TILE_SIZE, pos_j+TILE_SIZE);
                    box.draw(canvas);
                    System.out.println("pos_i:"+pos_i+" pos_j:"+pos_j+" right:"+(pos_i+TILE_SIZE)+" bottom:"+ (pos_j+TILE_SIZE));
                }
            }
        }
    }
}
