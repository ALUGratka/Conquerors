package pl.conquerors.app.view.createGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.Button;


import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.navigation.Navigator;

public class createGameActivity extends BaseActivity implements createGameView {

    public static Intent getStartingIntents(Context context){
        return new Intent(context, createGameActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

    }

    @OnClick(R.id.backButton)
    public void onPreviousButtonClicked(){
        Navigator.startHome(this);
    }

    @OnClick(R.id.forthButton)
    public void onNextButtonClicked(){
        Navigator.startChooseOpponent(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


}
