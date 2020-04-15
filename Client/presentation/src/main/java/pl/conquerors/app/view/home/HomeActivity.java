package pl.conquerors.app.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.homePrizeButton)
    Button mHomePrizeButton;

    @BindView(R.id.homeCreatorButton)
    Button mHomeCreatorButton;

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

        mNavigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

        homePresenter = new HomePresenter();
        homePresenter.setmView(this);

        homePresenter.created();
    }

    @OnClick(R.id.homePrizeButton)
    public void onPrizeButtonClicked() {
        Navigator.startPrize(this);
    }

    @OnClick(R.id.homeCreatorButton)
    public void onCreatorButtonClicked() {
        Navigator.startCharacterSexSelection(this);
    }

    @Override
    public void showLoading() {
        
    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void setNavigationButtonsVisibility(boolean isLoggedIn) {

    }

    @Override
    public void showMyProfile() {
        Navigator.startMyProfile(this);
    }

    @Override
    public void showLogout() {

    }

    @Override
    public boolean isDrawerOpen() {
        return false;
    }

    @Override
    public void closeDrawer() {

    }
}
