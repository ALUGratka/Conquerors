package pl.conquerors.app.view.home;

import pl.conquerors.app.base.BaseView;

public interface HomeView extends BaseView {

    void setNavigationButtonsVisibility(boolean isLoggedIn);
    void onNativeBackPressed();

    void showMyProfile();
    void showLogout();

    boolean isDrawerOpen();
    void closeDrawer();
}
