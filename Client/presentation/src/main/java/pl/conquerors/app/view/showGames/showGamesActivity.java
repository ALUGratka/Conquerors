package pl.conquerors.app.view.showGames;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.view.createGame.createGameActivity;

public class showGamesActivity extends BaseActivity implements showGamesView {

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    public static Intent getStartingIntents(Context context){
        return new Intent(context, showGamesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_games);
        TextView gameId = (TextView)findViewById(R.id.gameIdValue);
        TextView opponent = (TextView)findViewById(R.id.opponentValue);
        TextView character = (TextView)findViewById(R.id.characterValue);
        TextView date = (TextView)findViewById(R.id.dateValue);

        gameId.setText("1");
        opponent.setText("opponent name");
        character.setText("Male character");
        date.setText(String.valueOf(Calendar.getInstance().getTime()));

    }

    @OnClick(R.id.backButton)
    public void onBackButtonClicker(){
        Navigator.startHome(this);
    }
}
