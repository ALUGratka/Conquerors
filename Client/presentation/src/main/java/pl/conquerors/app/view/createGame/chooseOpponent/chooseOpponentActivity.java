package pl.conquerors.app.view.createGame.chooseOpponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.navigation.Navigator;

public class chooseOpponentActivity extends BaseActivity implements chooseOpponentView{
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public static Intent getStartingIntents(Context context){
        return new Intent(context, chooseOpponentActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_choose_opponent);

    }

    @OnClick(R.id.backButton)
    public void onPreviousButtonClicker(){
        Navigator.startAddGame(this);
    }
    @OnClick(R.id.forthButton)
    public void onNextButtonClicker(){
        Navigator.startHome(this);
        Toast.makeText(this, getString(R.string.game_created), Toast.LENGTH_SHORT).show();
    }


}
