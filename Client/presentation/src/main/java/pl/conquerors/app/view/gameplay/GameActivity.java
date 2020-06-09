package pl.conquerors.app.view.gameplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.view.createGame.chooseCharacter.ChooseCharacterActivity;

public class GameActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Game(this));
    }

    public static Intent getStartingIntents(Context context){
        return new Intent(context, GameActivity.class);
    }
}
