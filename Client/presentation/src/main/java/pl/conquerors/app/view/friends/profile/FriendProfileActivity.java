package pl.conquerors.app.view.friends.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.domain.model.UserRelationship;
import pl.conquerors.app.util.DialogUtil;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class FriendProfileActivity extends BaseActivity implements FriendProfileView {

    private static final String USER_ID = FriendProfileActivity.class.getSimpleName() + "_USER_ID";
    private static User friendUser;

    @BindView(R.id.friend_name)
    TextView name;

    @BindView(R.id.friend_points)
    TextView points;

    @BindView(R.id.friend_characters)
    TextView characters;

    @BindView(R.id.add_friend_button)
    Button addFriendButton;

    @BindView(R.id.delete_friend_button)
    Button deleteFriendButton;

    @BindView(R.id.accept_friend_button)
    Button acceptFriendButton;

    @BindView(R.id.reject_friend_button)
    Button rejectFriendButton;

    @BindView(R.id.uninvite_friend_button)
    Button uninviteFriendButton;

    private FriendProfilePresenter profilePresenter;

    @OnClick(R.id.add_friend_button)
    protected void onAddButtonClicked(){
        DialogUtil.showSimpleDialog(this, getString(R.string.add_friend_title), getString(R.string.add_friend_message), R.string.dialog_button_yes, (dialog, which) -> profilePresenter.attemptToAddFriend(),
                R.string.dialog_button_no, null);
    }

    @OnClick(R.id.delete_friend_button)
    protected void onDeleteButtonClicked(){
        DialogUtil.showSimpleDialog(this, getString(R.string.delete_friend_title), getString(R.string.delete_friend_message), R.string.dialog_button_yes, (dialog, which) -> profilePresenter.attemptToDeleteFriend(),
                R.string.dialog_button_no, null);
    }

    @OnClick(R.id.accept_friend_button)
    protected void onAcceptButtonClicked(){
        DialogUtil.showSimpleDialog(this, getString(R.string.accept_friend_title), getString(R.string.accept_friend_message), R.string.dialog_button_yes, (dialog, which) -> profilePresenter.attemptToAcceptFriend(),
                R.string.dialog_button_no, null);
    }

    @OnClick(R.id.reject_friend_button)
    protected void onRejectButtonClicked(){
        DialogUtil.showSimpleDialog(this, getString(R.string.ignore_title), getString(R.string.ignore_message), R.string.dialog_button_yes, (dialog, which) -> profilePresenter.attemptToIgnoreFriend(),
                R.string.dialog_button_no, null);
    }

    @OnClick(R.id.uninvite_friend_button)
    protected void onUninviteButtonClicked(){
        DialogUtil.showSimpleDialog(this, getString(R.string.uninvit_title), getString(R.string.uninvit_message), R.string.dialog_button_yes, (dialog, which) -> profilePresenter.attemptToUnInviteFriend(),
                R.string.dialog_button_no, null);
    }

    public static Intent getStartingIntent(Context context, final User user) {
        final Intent startingIntent = new Intent(context, FriendProfileActivity.class);
        friendUser = user;

        return startingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        profilePresenter = new FriendProfilePresenter();
        profilePresenter.setmView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        profilePresenter.resume();
    }

    @Override
    protected void onPause() {
        profilePresenter.pause();
        super.onPause();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public User getUser() {
        return friendUser;
    }

    @Override
    public void setUser(User user) {
        friendUser = user;
    }

    @Override
    public void showProfileDetails(final User user) {
        name.setText(user.getUserNick());
        points.setText(user.getUserPoints());
        //characters.setText(user.getCharacters().size());
    }

    @Override
    public void setProfileActions(final boolean visible) {

    }

    @Override
    public void onActionComplete(String title, String message) {
        DialogUtil.showSimpleDialog(this, title, message).setOnDismissListener(dialog -> profilePresenter.loadData());
    }

    @Override
    public Long getCurrentUserId() {
        return SharedPreferenceUtil.getUser(this).getUserId();
    }



    @Override
    public void setupActionButton(final UserRelationship usersRelationship) {
        addFriendButton.setVisibility(usersRelationship.getCanInvite() ? View.VISIBLE : View.GONE);
        deleteFriendButton.setVisibility(usersRelationship.getCanDelete() ? View.VISIBLE : View.GONE);
        acceptFriendButton.setVisibility(usersRelationship.getCanAccept() ? View.VISIBLE : View.GONE);
        rejectFriendButton.setVisibility(usersRelationship.getCanReject() ? View.VISIBLE : View.GONE);
        uninviteFriendButton.setVisibility(usersRelationship.getCanUninvite() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNumberOfCharacters(int numberOfCharacters) {
        characters.setText(String.valueOf(numberOfCharacters));
    }
}
