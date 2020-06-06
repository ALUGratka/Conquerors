package pl.conquerors.app.view.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.homePrizeButton)
    Button mHomePrizeButton;

    @BindView(R.id.homeCreatorButton)
    Button mHomeCreatorButton;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private HomePresenter homePresenter;

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            homePresenter.onDrawerItemSelected(menuItem);
            return true;
        }
    };

    public static Intent getStartingIntents(final Context context){
        final Intent startingIntent = new Intent(context, HomeActivity.class);
        return startingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.action_next, R.string.action_back);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

        homePresenter = new HomePresenter();
        homePresenter.setmView(this);

        homePresenter.created();
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.resume();
    }

    @Override
    public void onNativeBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.homePrizeButton)
    public void onPrizeButtonClicked() {
        Navigator.startPrize(this);
    }

    @OnClick(R.id.homeCreatorButton)
    public void onCreatorButtonClicked() {
        Navigator.startCreateCharacter(this);
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public void setNavigationButtonsVisibility(boolean isLoggedIn) {

    }

    @Override
    public void showMyProfile() {
        Navigator.startMyProfile(this);
    }

    @Override
    public void showMyFriends() { Navigator.startFriends(this); }

    @Override
    public void showMyGames() {Navigator.startShowGames(this);}

    @Override
    public void showAddGame() {Navigator.startAddGame(this);}

    @Override
    public void showSettings() { Navigator.startSettings(this); }

    @Override
    public void showLogout() {
        DialogUtil.showSimpleDialog(
                HomeActivity.this,
                getString(R.string.dialog_logout_title),
                getString(R.string.dialog_logout_message),
                getLogoutPositiveListener(),
                null); // We don't need here anything more than default behaviour which is dismissing the dialog.
    }

    private DialogInterface.OnClickListener getLogoutPositiveListener() {
        return (dialog, which) ->  logout();
    }

    private void logout(){
        SharedPreferenceUtil.setLoggedIn(this,false);
        SharedPreferenceUtil.setUserName(this,null);
        SharedPreferenceUtil.setUser(this,null);
        finish();
        Navigator.startLogin(this.getContext());
    }

    @Override
    public boolean isDrawerOpen() { return mDrawer.isDrawerOpen(GravityCompat.START); }

    @Override
    public void closeDrawer() {  mDrawer.closeDrawer(GravityCompat.START); }
}
