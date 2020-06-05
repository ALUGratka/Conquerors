package pl.conquerors.app.view.gameplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private Character character;
    private Point characterPoint;
    private Map mapCanvas;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Game(Context context){
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        character = new Character(new Rect(100, 100, 200, 200), Color.WHITE);
        characterPoint = new Point(2,15);
        mapCanvas = new Map(context);
        setFocusable(true);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while(true){
            try{
                thread.setRunning(false);
                thread.join();
            }
            catch(Exception e) { e.printStackTrace();}
            retry = false;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x= character.getTile((int)event.getX(), (int)event.getY())[0];
                int y= character.getTile((int)event.getX(), (int)event.getY())[1];
                System.out.println(x + " " + y);
                characterPoint.set(x,y);

        }
        return true;
        // return super.onTouchEvent(event);
    }

    public void update(){
        character.update(characterPoint);
    }


    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        mapCanvas.showMap(canvas);
       // character.draw(canvas);
    }
}
