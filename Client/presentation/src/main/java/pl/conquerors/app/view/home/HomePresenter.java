package pl.conquerors.app.view.home;

import android.view.MenuItem;

import java.util.List;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.Gameplay;
import pl.conquerors.app.model.GameplayEntity;
import pl.conquerors.app.model.mapper.GameplayEntityMapper;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.view.gameplay.GameActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter() {}

    @Override
    public void created() {
        super.created();
        attemptToLoadAllGames();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
    }

    public void onDrawerItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.nav_profile:
                mView.showMyProfile();
                break;
            case R.id.nav_settings:
                mView.showSettings();
                break;
            case R.id.nav_logout:
                mView.showLogout();
                break;
            case R.id.nav_add_game:
                mView.showAddGame();
                break;
            case R.id.nav_my_games:
                mView.showMyGames();
                break;
            case R.id.nav_friends:
                mView.showMyFriends();
                break;
            case R.id.nav_my_characters:
                mView.showMyCharacters();
                break;
            case R.id.nav_add_character:
                mView.showAddCharacter();
                break;
        }
        mView.closeDrawer();
    }

    private void attemptToLoadAllGames() {
        final long userId = mView.getUserId();

        Call<List<GameplayEntity>> call = RestClient.getInstance().getGameplays(userId);

        call.enqueue(new Callback<List<GameplayEntity>>() {
            @Override
            public void onResponse(Call<List<GameplayEntity>> call, Response<List<GameplayEntity>> response) {
                if(response.body()==null){
                    mView.startCreateGame(true);
                    mView.setGameCardVisible(false);
                    mView.setArrowsVisible(false);
                    mView.setPlayButtonVisible(false);
                    mView.setNotYourTurnButtonVisible(false);
                }
                else {
                    List<Gameplay> gameplays = GameplayEntityMapper.transform(response.body());
                    if(gameplays.size()==1) mView.setArrowsVisible(false);
                    mView.setGameCard(0);
                }
            }

            @Override
            public void onFailure(Call<List<GameplayEntity>> call, Throwable t) {

            }
        });

        mView.startCreateGame(false);
        mView.setGameCardVisible(true);
        mView.setArrowsVisible(true);
        mView.setPlayButtonVisible(true);
        mView.setNotYourTurnButtonVisible(false);
    }

    void attemptToGetGames(){
        final long userId = mView.getUserId();


    }
}
