package pl.conquerors.app.view.friends.search;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindFriendPresenter extends BasePresenter<FindFriendView> {

    private List<User>usersInDatabase = new ArrayList<>();

    @Override
    public void created() {
        super.created();
    }

    public void attemptFindFriend() {
        mView.showLoading();
        mView.setContentShown(false);

        final String query = mView.getSearchQuery();
        final long userId = SharedPreferenceUtil.getUser(mView.getContext()).getUserId();

        Call<List<UserEntity>> call = RestClient.getInstance().findFriends(query,userId);

        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if(response.code() == 204) {
                    mView.showNoResultsMessage();
                }
                else {
                    List<User>friends = UserEntityMapper.transform(response.body());
                    mView.showSearchResult(friends);
                    mView.setContentShown(true);
                }
            }
            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                handleError(t);
            }
        });

    }
}
