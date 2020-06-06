package pl.conquerors.app.view.createGame.chooseOpponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.model.EnemiesAchievementEntity;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        createNewMap();
        Navigator.startHome(this);
        Toast.makeText(this, getString(R.string.game_created), Toast.LENGTH_SHORT).show();
    }

    protected void createNewMap()
    {
        int gameId =1;
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
