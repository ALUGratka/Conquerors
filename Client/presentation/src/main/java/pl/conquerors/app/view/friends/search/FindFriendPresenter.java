package pl.conquerors.app.view.friends.search;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindFriendPresenter extends BasePresenter<FindFriendView> {

    private List<User>usersInDatabase = new ArrayList<>();

    private void initTestData(){

        User user1 = new User();
        user1.setUserNick("werka");
        user1.setUserId(1);

        usersInDatabase.add(user1);
    }

    @Override
    public void created() {
        initTestData();
        super.created();
    }

    public void attemptFindFriend() {
        mView.showLoading();
        mView.setContentShown(false);

        final String query = mView.getSearchQuery();

        List<User>findings = new ArrayList<>();

        for(User user:usersInDatabase){
            if(user.getUserNick().contains(query))
                findings.add(user);
        }

        if(findings.isEmpty()){
            mView.showNoResultsMessage();
        }
        else {
            mView.showSearchResult(findings);
            mView.setContentShown(true);
        }

        Call<List<UserEntity>> call = RestClient.getInstance().findFriends(query);

        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                if(response.body()==null || response.body().isEmpty()) {
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
