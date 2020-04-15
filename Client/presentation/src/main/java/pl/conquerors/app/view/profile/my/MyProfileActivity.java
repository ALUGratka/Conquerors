package pl.conquerors.app.view.profile.my;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.view.register.RegistrationActivity;

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
    protected void showMyAvards() {
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
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
