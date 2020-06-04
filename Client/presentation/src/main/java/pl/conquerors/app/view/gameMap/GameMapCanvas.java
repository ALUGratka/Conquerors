package pl.conquerors.app.view.gameMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import pl.conquerors.app.R;

public class GameMapCanvas extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    public GameMapCanvas(Context context) {
        super(context);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceholder) {
                Canvas canvas = holder.lockCanvas(null);
                draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceholder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceholder) {

            }
        });
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.skarb);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        canvas.drawBitmap(bmp, 300, 400, null);
        canvas.drawBitmap(bmp, 100, 400, null);
    }
}
