package pl.conquerors.app.view.friends.profile;

import android.util.Log;

import java.util.ArrayList;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendProfilePresenter extends BasePresenter<FriendProfileView> {

    public FriendProfilePresenter() { }

    @Override
    public void resume() {
        super.resume();
        loadData();
    }

    public void loadData() {
        final Long userId = mView.getUserId();
        Log.i("userID", String.valueOf(userId));
        if(userId!=null){
            showProfile(userId);
        }
    }

    private void showProfile(final Long userId) {
        mView.showLoading();

        User user = new User();
        user.setUserId(userId);
        user.setUserNick("Maciej");
        user.setUserPoints("1234");
        user.setCanInvite(false);
        user.setCanAccept(true);
        user.setCanRemove(false);
        user.setCanIgnore(true);
        user.setCharacters(new ArrayList<>());

        mView.setupActionButton(user);
        mView.setCurrentUserId(user.getUserId());
        mView.setProfileActions(shouldShowActions(user));
        mView.showProfileDetails(user);


        /*Call<UserEntity> call = RestClient.getInstance().getFriend(userId);
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                User user = UserEntityMapper.transform(response.body());
                mView.setCurrentUserId(user.getUserId());
                mView.setProfileActions(shouldShowActions(user));
                mView.showProfileDetails(user);
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                handleError(t);
            }
        });*/

    }

    void attemptToAddFriend() {
        mView.showLoading();
        Call<Void> call = RestClient.getInstance().addFriend(mView.getUserId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mView.onActionComplete(mView.getContext().getString(R.string.add_friend_title), getString(R.string.added_friend_message));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                handleError(t);
            }
        });
    }

    void attemptToDeleteFriend() {
        mView.showLoading();

        Call<Void> call = RestClient.getInstance().deleteFriend(mView.getUserId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mView.onActionComplete(getString(R.string.delete_friend_title), getString(R.string.deleted_friend_message));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    void attemptToAcceptFriend() {
        mView.showLoading();

        Call<Void> call = RestClient.getInstance().acceptFriend(mView.getUserId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mView.onActionComplete(getString(R.string.accept_friend_title), getString(R.string.accepted_friend_message));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    void attemptToIgnoreFriend() {
        mView.showLoading();

        Call<Void> call = RestClient.getInstance().ignoreFriend(mView.getUserId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mView.hideLoading();
                mView.onActionComplete(getString(R.string.ignore_title), getString(R.string.ignored_message));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private boolean shouldShowActions(final User user) {
        return user.canInvite() || user.canIgnore() || user.canRemove() || user.canAccept();
    }

    private String getString(final int resId) {
        return mView.getContext().getString(resId);
    }
}
