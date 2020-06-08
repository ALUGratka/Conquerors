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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.hello_user_textView)
    TextView helloUser;

    @BindView(R.id.previousButton)
    Button previousArrowButton;

    @BindView(R.id.nextButton)
    Button nextArrowButton;

    @BindView(R.id.game_layout)
    LinearLayout gameLayout;

    @BindView(R.id.no_game_layout)
    LinearLayout noGamePopUp;

    @BindView(R.id.play_button)
    Button playButton;

    @BindView(R.id.not_your_turn_button)
    Button notYourTurnButton;

    @OnClick(R.id.no_characters_button)
    protected void onNoGameButtonClicked() { Navigator.startAddGame(this);}

    @OnClick(R.id.daily_prize_button)
    protected void startDailyPrize(){
        Navigator.startPrize(this);
    }

    @OnClick(R.id.previousButton)
    protected void onPreviousArrowClicked() {
        if(counter!=-1){
            counter--;
        }
        setGameCard(counter);
    }

    @OnClick(R.id.nextButton)
    protected void onNextArrowClicked() {
        if(counter!= games.size()) counter++;
        setGameCard(counter);
    }

    private List<Integer>games = new ArrayList<>();
    //TODO List<Gameplay>games = new ArrayList<>();
    private int counter = 0;
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

        counter = 0;
        setHelloUser();

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

    @Override
    public long getUserId() {
        return SharedPreferenceUtil.getUser(this).getUserId();
    }


    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

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
    public void showMyCharacters() { Navigator.startMyCharacters(this);}

    @Override
    public void showAddCharacter() { Navigator.startCreateCharacter(this); }

    @Override
    public void setHelloUser() {
        helloUser.setText(getString(R.string.home_hello_user).concat(" ").concat(SharedPreferenceUtil.getUser(this).getUserNick()));
    }

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

    @Override
    public void startCreateGame(boolean visible) {
        noGamePopUp.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setGameCard(int index) {
        //TODO set game information


        setArrowsVisible(true);
        if(counter==0) setPreviousArrowsVisible(false);
        else if(counter == games.size()-1) setNextArrowVisible(false);
    }

    @Override
    public void setPreviousArrowsVisible(boolean visible) {
        previousArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNextArrowVisible(boolean visible) {
        nextArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setArrowsVisible(boolean visible) {
        previousArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
        nextArrowButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setGameCardVisible(boolean visible) {
        gameLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPlayButtonVisible(boolean visible) {
        playButton.setVisibility(visible? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNotYourTurnButtonVisible(boolean visible) {
        notYourTurnButton.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
