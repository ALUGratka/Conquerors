package pl.conquerors.app.view.home;

import android.view.MenuItem;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.navigation.Navigator;
import rx.Subscriber;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter() {}

    @Override
    public void created() {
        super.created();
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
}
