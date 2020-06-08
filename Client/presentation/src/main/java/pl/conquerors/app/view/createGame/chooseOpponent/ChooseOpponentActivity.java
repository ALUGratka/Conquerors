package pl.conquerors.app.view.createGame.chooseOpponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.Gameplay;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.EnemiesAchievementEntity;
import pl.conquerors.app.model.GameplayEntity;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.createGame.chooseCharacter.ChooseCharacterPresenter;
import pl.conquerors.app.view.gameplay.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseOpponentActivity extends BaseActivity implements ChooseOpponentView {

    private ChooseOpponentAdapter adapter;
    private ChooseOpponentPresenter presenter;

    private User opponent;

    @OnClick(R.id.backButton)
    public void onPreviousButtonClicker(){
        Navigator.startAddGame(this);
    }

    @OnClick(R.id.forthButton)
    public void onNextButtonClicker(){
        if(SharedPreferenceUtil.getOpponent(this) ==null) {
            Toast.makeText(this, getString(R.string.create_game_no_opponent_choose_error), Toast.LENGTH_SHORT).show();
        }
        else {
            //TODO send game info to database
            final int userId = (int)SharedPreferenceUtil.getUser(this).getUserId();
            Character character = SharedPreferenceUtil.getGameCharacter(this);
            User opponent = SharedPreferenceUtil.getOpponent(this);

            GameplayEntity gameplay = new GameplayEntity(0, userId, (int)opponent.getUserId(), character.getmId(), 0, 1, 1, 2, 15, 8,2, false, false, false,  true);

            Call<GameplayEntity> call = RestClient.getInstance().createGameplay(gameplay);

            call.enqueue(new Callback<GameplayEntity>(){

                @Override
                public void onResponse(Call<GameplayEntity> call, Response<GameplayEntity> response) {
                        Log.e("Gameplay created", "Code: " + response.code());
                        createNewMap(response.body().getId());
                }

                @Override
                public void onFailure(Call<GameplayEntity> call, Throwable t) {
                        Log.e("Gameplay error", t.getMessage());
                }
            });

            finish();
            Toast.makeText(this, getString(R.string.game_created), Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getStartingIntents(Context context){
        return new Intent(context, ChooseOpponentActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_choose_opponent);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ChooseOpponentAdapter();
        recyclerView.setAdapter(adapter);

        presenter = new ChooseOpponentPresenter();
        presenter.setmView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferenceUtil.setOpponent(this,null);
        Navigator.startAddGame(this);
    }

    @Override
    public Long getUserId() {
        return SharedPreferenceUtil.getUser(this).getUserId();
    }

    @Override
    public void showFriends(List<User> friends) {
        adapter.setItems(friends);
    }

    @Override
    public void setOpponent() {
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    protected void createNewMap(int gameId)
    {
        int treasureId, enemyId;

        int rows =30;
        int columns = 20;
        Random random = new Random();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                int r  = random.nextInt(10);
                if(r%10==1) {
                    treasureId=random.nextInt(15)+1;
                    createTreasureAchievement(gameId, treasureId, String.valueOf(i), String.valueOf(j));
                }
                else if(r%10==2) {
                    enemyId=random.nextInt(15)+1;
                    createEnemiesAchievement(gameId, enemyId, String.valueOf(i), String.valueOf(j));
                }
            }
        }
    }

    public void createTreasureAchievement(int gameId, int treasureId, String objectPositionX,
                                          String objectPositionY) {
        Call<TreasureAchievementEntity> call =
                RestClient.getInstance().createTreasuresAchievement
                        (new TreasureAchievementEntity(gameId, treasureId, objectPositionX,
                                objectPositionY));

        call.enqueue(new Callback<TreasureAchievementEntity>() {
            @Override
            public void onResponse(Call<TreasureAchievementEntity> call, Response<TreasureAchievementEntity> response) {
                if (!response.isSuccessful()) {
                    Log.e("Treasure Achievement", "Code: " + response.code());
                    return;
                }
                Log.e("Treasure created!", "Code: " + response.code());
            }
            @Override
            public void onFailure(Call<TreasureAchievementEntity> call, Throwable t) {
                Log.e("Treasure Achievement", t.getMessage());
            }
        });
    }

    public void createEnemiesAchievement(int gameId, int enemyId, String objectPositionX,
                                         String objectPositionY) {
        Call<EnemiesAchievementEntity> call =
                RestClient.getInstance().createEnemiesAchievement
                        (new EnemiesAchievementEntity(gameId, enemyId, objectPositionX,
                                objectPositionY));

        call.enqueue(new Callback<EnemiesAchievementEntity>() {
            @Override
            public void onResponse(Call<EnemiesAchievementEntity> call, Response<EnemiesAchievementEntity> response) {
                if (!response.isSuccessful()) {
                    Log.e("Enemies Achievement", "Code: " + response.code());
                    return;
                }
                Log.e("Enemies created!", "Code: " + response.code());
            }
            @Override
            public void onFailure(Call<EnemiesAchievementEntity> call, Throwable t) {
                Log.e("Treasure Achievement", t.getMessage());
            }
        });
    }
}