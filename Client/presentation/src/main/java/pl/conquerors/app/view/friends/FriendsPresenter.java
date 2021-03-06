package pl.conquerors.app.view.friends;

import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsPresenter extends BasePresenter<FriendsView> {

    public FriendsPresenter() { }

    @Override
    public void resume() {
        super.resume();
        loadFriendsData();
    }

    private void loadFriendsData() {
        Long userId = mView.getUserId();
        showFriends(userId);
        loadInvitations();
    }

    private void showFriends(final Long userId) {
        mView.showLoading();
        Call<List<UserEntity>> call = RestClient.getInstance().getAllFriends(userId);

        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if(response.body() != null)  {
                    List<User> users = UserEntityMapper.transform(response.body());
                    if(users.isEmpty()){
                        mView.setFriendsVisible(false);
                        mView.setNoFriendsVisible(true);
                    }
                    else{
                        mView.setFriendsVisible(true);
                        mView.setNoFriendsVisible(false);
                        mView.showFriends(users);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                handleError(t);
            }
        });
    }

    public void loadInvitations() {
        mView.showLoading();

        Call<List<UserEntity>> call = RestClient.getInstance().getAllInvitations(mView.getUserId());

        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if(response.body() != null)  {
                    List<User> users = UserEntityMapper.transform(response.body());
                    mView.showInvitations(users);
                    mView.setInvitationVisible(true);
                }
                else mView.setInvitationVisible(false);
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                handleError(t);
            }
        });
    }
}
