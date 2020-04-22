package pl.conquerors.app.view.profile.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;

public class MyProfileActivity extends BaseActivity implements MyProfileView {

    @BindView(R.id.profile_avatar)
    ImageView mAvatar;

    @BindView(R.id.layout_my_character)
    LinearLayout mCharactersLayout;

    @BindView(R.id.layout_my_games)
    LinearLayout mGamesLayout;

    @BindView(R.id.layout_my_awards)
    LinearLayout mAwardsLayout;

    @BindView(R.id.layout_my_friends)
    LinearLayout mFriendsLayout;

    @BindView(R.id.profile_name)
    TextView mProfileName;

    private MenuItem mEditProfileItem;
    private MyProfilePresenter myProfilePresenter;

    @OnClick(R.id.layout_my_character)
    protected void showMyCharacters() {
        mCharactersLayout.setBackgroundResource(R.color.theme_transparent_grey);
        Navigator.startHome(this.getContext());
        finish();
        //TODO show my characters
    }

    @OnClick(R.id.layout_my_games)
    protected void showMyGames() {
        //TODO show my characters
    }

    @OnClick(R.id.layout_my_awards)
    protected void showMyAvatars() {
        //TODO show my characters
    }

    @OnClick(R.id.layout_my_friends)
    protected void showMyFriends() {
        //TODO show my characters
    }

    public static Intent getStartingIntents(Context context) {
        Intent startingIntent = new Intent(context, MyProfileActivity.class);
        return startingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        myProfilePresenter = new MyProfilePresenter();
        myProfilePresenter.setmView(this);
        myProfilePresenter.created();

        myProfilePresenter.getProfileName();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_details, menu);
        mEditProfileItem = menu.findItem(R.id.action_edit_profile);
        if (mEditProfileItem != null) {
            myProfilePresenter.setupEditMenu();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                Navigator.startSettings(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public void setUserName(final String name) {
        mProfileName.setText(name);
    }

    @Override
    public void showEditButton() {
        mEditProfileItem.setVisible(true);
    }

    @Override
    public void hideEditButton() {
        mEditProfileItem.setVisible(false);
    }

}
