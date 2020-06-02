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
import pl.conquerors.app.util.DialogUtil;

public class FriendProfileActivity extends BaseActivity implements FriendProfileView {

    private static final String USER_ID = FriendProfileActivity.class.getSimpleName() + "_USER_ID";

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

    private boolean actionsVisible = false;

    public static Intent getStartingIntent(Context context, final long userId) {
        final Intent startingIntent = new Intent(context, FriendProfileActivity.class);
        startingIntent.putExtra(USER_ID, userId);

        return startingIntent;
    }

    @OnClick(R.id.reject_friend_button)
    protected void onRejectButtonClicked(){
        DialogUtil.showSimpleDialog(this, getString(R.string.ignore_title), getString(R.string.ignore_message), R.string.dialog_button_yes, (dialog, which) -> profilePresenter.attemptToIgnoreFriend(),
                R.string.dialog_button_no, null);
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
    public Long getUserId() {
        final Bundle bundle = getIntent().getExtras();
        return bundle != null ? bundle.getLong(USER_ID) : null;
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
    public void setCurrentUserId(long id) {

    }

    @Override
    public void setupActionButton(final User user) {
        addFriendButton.setVisibility(user.canInvite() ? View.VISIBLE : View.GONE);
        deleteFriendButton.setVisibility(user.canRemove() ? View.VISIBLE : View.GONE);
        acceptFriendButton.setVisibility(user.canAccept() ? View.VISIBLE : View.GONE);
        rejectFriendButton.setVisibility(user.canIgnore() ? View.VISIBLE : View.GONE);
    }
}
