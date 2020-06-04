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
        bmp = replaceColor(bmp);
        canvas.drawBitmap(bmp, 300, 400, null);
        canvas.drawBitmap(bmp, 100, 400, null);
        canvas.drawBitmap(bmp, 100, 100, null);
    }

    public Bitmap replaceColor(Bitmap src) {
        if (src == null)
            return null;
        int width = src.getWidth();
        int height = src.getHeight();
        int[] pixels = new int[width * height];
        src.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int x = 0; x < pixels.length; ++x) {
            if(pixels[x] == Color.WHITE) pixels[x] = 0;
        }
        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888);
    }
}
