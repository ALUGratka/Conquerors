package pl.conquerors.app.view.friends.profile;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.domain.model.UserRelationship;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.UserRelationshipEntity;
import pl.conquerors.app.model.mapper.CharacterEntityMapper;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.model.mapper.UserRelationshipMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendProfilePresenter extends BasePresenter<FriendProfileView> {

    UserRelationship userRelationship = new UserRelationship();

    FriendProfilePresenter() { }

    @Override
    public void resume() {
        super.resume();
        loadData();
    }

    public void loadData() {
        final Long userId = mView.getUser().getUserId();
        Log.i("userID", String.valueOf(userId));
        if(userId!=null){
            showProfile();
            attemptToGetUserCharactersNumber();
            getUserActions(mView.getUser());
        }
    }

    private void showProfile() {
        mView.showLoading();

        Call<UserEntity> call = RestClient.getInstance().getFriend(mView.getUser().getUserId());
        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                User user = UserEntityMapper.transform(response.body());
                mView.setUser(user);
                mView.showProfileDetails(user);
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                handleError(t);
            }
        });

    }

    private void attemptToGetUserCharactersNumber(){
        mView.showLoading();

        Call<List<CharacterEntity>> call = RestClient.getInstance().getCharacter((int)mView.getUser().getUserId());

        call.enqueue(new Callback<List<CharacterEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CharacterEntity>> call, Response<List<CharacterEntity>> response) {
                mView.setNumberOfCharacters(response.body().size());
            }

            @Override
            public void onFailure(Call<List<CharacterEntity>> call, Throwable t) {
                handleError(t);
            }
        });

    }

    private void getUserActions(final User user) {
        Call<UserRelationshipEntity> call = RestClient.getInstance().checkUsersRelationship(mView.getCurrentUserId(), user.getUserId());

        call.enqueue(new Callback<UserRelationshipEntity>() {
            @Override
            public void onResponse(Call<UserRelationshipEntity> call, Response<UserRelationshipEntity> response) {
                UserRelationship userRelationship = UserRelationshipMapper.transform(response.body());
                mView.setupActionButton(userRelationship);
            }

            @Override
            public void onFailure(Call<UserRelationshipEntity> call, Throwable t) {
                handleError(t);
            }
        });
    }

    void attemptToAddFriend() {
        mView.showLoading();

        final UserRelationshipEntity userRelationshipEntity = new UserRelationshipEntity();

        userRelationshipEntity.setUser1Id(mView.getCurrentUserId());
        userRelationshipEntity.setUser2Id((int)mView.getUser().getUserId());
        userRelationshipEntity.setCanInvite(false);
        userRelationshipEntity.setCanDelete(false);
        userRelationshipEntity.setCanAccept(false);
        userRelationshipEntity.setCanReject(false);
        userRelationshipEntity.setCanUninvit(true);

        Call<UserRelationshipEntity> call = RestClient.getInstance().addFriend(userRelationshipEntity);
        call.enqueue(new Callback<UserRelationshipEntity>() {
            @Override
            public void onResponse(Call<UserRelationshipEntity> call, Response<UserRelationshipEntity> response) {
                UserRelationship userRelationship = UserRelationshipMapper.transform(response.body());
                mView.setupActionButton(userRelationship);
                Toast.makeText(mView.getContext(), getString(R.string.added_friend_message),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserRelationshipEntity> call, Throwable t) {
                handleError(t);
            }
        });

    }

    void attemptToDeleteFriend() {
        mView.showLoading();

        final UserRelationshipEntity userRelationshipEntity = new UserRelationshipEntity();

        userRelationshipEntity.setUser1Id(mView.getCurrentUserId());
        userRelationshipEntity.setUser2Id((int)mView.getUser().getUserId());

        Call<UserRelationshipEntity> call = RestClient.getInstance().deleteFriend(userRelationshipEntity);
        call.enqueue(new Callback<UserRelationshipEntity>() {
            @Override
            public void onResponse(Call<UserRelationshipEntity> call, Response<UserRelationshipEntity> response) {
                UserRelationship userRelationship = UserRelationshipMapper.transform(response.body());
                mView.setupActionButton(userRelationship);
                Toast.makeText(mView.getContext(), getString(R.string.deleted_friend_message),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserRelationshipEntity> call, Throwable t) {
                handleError(t);
            }
        });
    }

    void attemptToAcceptFriend() {
        mView.showLoading();

        final UserRelationshipEntity userRelationshipEntity = new UserRelationshipEntity();

        userRelationshipEntity.setUser1Id(mView.getCurrentUserId());
        userRelationshipEntity.setUser2Id((int)mView.getUser().getUserId());

        Call<UserRelationshipEntity> call = RestClient.getInstance().acceptFriend(userRelationshipEntity);
        call.enqueue(new Callback<UserRelationshipEntity>() {
            @Override
            public void onResponse(Call<UserRelationshipEntity> call, Response<UserRelationshipEntity> response) {
                UserRelationship userRelationship = UserRelationshipMapper.transform(response.body());
                mView.setupActionButton(userRelationship);
                Toast.makeText(mView.getContext(), getString(R.string.accepted_friend_message),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserRelationshipEntity> call, Throwable t) {
                handleError(t);
            }
        });
    }

    void attemptToIgnoreFriend() {
        mView.showLoading();

        final UserRelationshipEntity userRelationshipEntity = new UserRelationshipEntity();

        userRelationshipEntity.setUser1Id(mView.getCurrentUserId());
        userRelationshipEntity.setUser2Id((int)mView.getUser().getUserId());

        Call<UserRelationshipEntity> call = RestClient.getInstance().deleteFriend(userRelationshipEntity);
        call.enqueue(new Callback<UserRelationshipEntity>() {
            @Override
            public void onResponse(Call<UserRelationshipEntity> call, Response<UserRelationshipEntity> response) {
                UserRelationship userRelationship = UserRelationshipMapper.transform(response.body());
                mView.setupActionButton(userRelationship);
                Toast.makeText(mView.getContext(), getString(R.string.ignored_message),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserRelationshipEntity> call, Throwable t) {
                handleError(t);
            }
        });
    }

    void attemptToUnInviteFriend() {
        mView.showLoading();

        final UserRelationshipEntity userRelationshipEntity = new UserRelationshipEntity();

        userRelationshipEntity.setUser1Id(mView.getCurrentUserId());
        userRelationshipEntity.setUser2Id((int)mView.getUser().getUserId());

        Call<UserRelationshipEntity> call = RestClient.getInstance().deleteFriend(userRelationshipEntity);
        call.enqueue(new Callback<UserRelationshipEntity>() {
            @Override
            public void onResponse(Call<UserRelationshipEntity> call, Response<UserRelationshipEntity> response) {
                UserRelationship userRelationship = UserRelationshipMapper.transform(response.body());
                mView.setupActionButton(userRelationship);
                Toast.makeText(mView.getContext(), getString(R.string.uninvited_message),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserRelationshipEntity> call, Throwable t) {
                handleError(t);
            }
        });
    }

    private String getString(final int resId) {
        return mView.getContext().getString(resId);
    }

}
