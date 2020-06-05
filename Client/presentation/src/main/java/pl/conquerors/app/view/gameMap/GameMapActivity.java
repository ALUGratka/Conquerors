package pl.conquerors.app.view.gameMap;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import pl.conquerors.app.base.BaseActivity;

public class GameMapActivity extends BaseActivity implements GameMapView {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameMapCanvas(this));
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
