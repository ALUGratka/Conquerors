package pl.conquerors.app.view.showGames;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.Gameplay;
import pl.conquerors.app.model.GameplayEntity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.model.mapper.GameplayEntityMapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class showGamesActivity extends BaseActivity implements showGamesView {
    List<Gameplay> gameplays;
    private showGamesPresenter presenter;
    int userId;
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, showGamesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        userId = (int) SharedPreferenceUtil.getUser(this.getContext()).getUserId();


        Call<List<GameplayEntity>> call = RestClient.getInstance().getGameplays(userId);
        call.enqueue(new Callback<List<GameplayEntity>>() {
            @Override
            public void onResponse(Call<List<GameplayEntity>> call, Response<List<GameplayEntity>> response) {
                Log.e("Gameplayes loaded", "Code: " + response.code());
                gameplays = GameplayEntityMapper.transform(response.body());
                for(Gameplay g : gameplays){
                    showInformation();
                }
            }

            @Override
            public void onFailure(Call<List<GameplayEntity>> call, Throwable t) {
                Log.e("Gameplayes load error", "Code: " + t.getMessage());
            }
        });

        setContentView(R.layout.activity_show_games);


    }

    @OnClick(R.id.backButton)
    public void onBackButtonClicker() {
        Navigator.startHome(this);
    }

    public void showInformation(){

        TextView round = (TextView) findViewById(R.id.roundText);
        TextView opponent = (TextView) findViewById(R.id.opponentValue);
        TextView character = (TextView) findViewById(R.id.characterValue);


        round.setText(String.valueOf(gameplays.get(0).getRound()));
        opponent.setText(String.valueOf(gameplays.get(0).getPlayer2id()));
        character.setText(String.valueOf(gameplays.get(0).getCharacter1id()));



    }
}
