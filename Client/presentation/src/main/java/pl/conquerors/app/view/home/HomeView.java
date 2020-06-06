package pl.conquerors.app.view.home;

import pl.conquerors.app.base.BaseView;

public interface HomeView extends BaseView {

    void setNavigationButtonsVisibility(boolean isLoggedIn);
    void onNativeBackPressed();

    void showMyGames();
    void showAddGame();
    void showMyProfile();
    void showMyFriends();
    void showLogout();
    void showSettings();

    boolean isDrawerOpen();
    void closeDrawer();
}
