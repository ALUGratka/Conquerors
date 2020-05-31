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
    }

    private void showFriends(final Long userId) {
        mView.showLoading();
        Call<List<UserEntity>> call = RestClient.getInstance().getAllFriends(userId);

        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                List<User> users = UserEntityMapper.transform(response.body());
                mView.showFriends(users);
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {

            }
        });
    }
}
