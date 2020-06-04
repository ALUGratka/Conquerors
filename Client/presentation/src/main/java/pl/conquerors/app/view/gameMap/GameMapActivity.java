package pl.conquerors.app.view.gameMap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;

public class GameMapActivity extends BaseActivity implements GameMapView {

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
