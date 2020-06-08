package pl.conquerors.app.view.home;

import pl.conquerors.app.base.BaseView;

public interface HomeView extends BaseView {

    void setNavigationButtonsVisibility(boolean isLoggedIn);
    void onNativeBackPressed();

    long getUserId();


    void showMyGames();
    void showAddGame();
    void showMyProfile();
    void showMyFriends();
    void showLogout();
    void showSettings();
    void showMyCharacters();
    void showAddCharacter();

    void setHelloUser();

    boolean isDrawerOpen();
    void closeDrawer();

    void startCreateGame(boolean visible);
    void setGameCard(int index);

    void setPreviousArrowsVisible(boolean visible);
    void setNextArrowVisible(boolean visible);
    void setArrowsVisible(boolean visible);
    void setGameCardVisible(boolean visible);
    void setPlayButtonVisible(boolean visible);
    void setNotYourTurnButtonVisible(boolean visible);
}
