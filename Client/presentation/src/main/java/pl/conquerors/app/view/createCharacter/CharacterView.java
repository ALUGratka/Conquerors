package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CharacterView extends View {
    Paint paint = new Paint();

    public CharacterView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        //Rect rect = new Rect(20, 56, 200, 112);

        //canvas.drawRect(rect, paint );
    }
}
