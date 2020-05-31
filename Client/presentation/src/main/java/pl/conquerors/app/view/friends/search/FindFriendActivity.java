package pl.conquerors.app.view.friends.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.List;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.User;

public class FindFriendActivity extends BaseActivity implements FindFriendView {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private SearchView mSearchView;

    private FindFriendPresenter mFindFriendPresenter;

    private FindFriendAdapter mFindFriendAdapter;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context,FindFriendActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getSearchQuery() {
        return null;
    }

    @Override
    public void showSearchResult(List<User> result) {

    }

    @Override
    public void showNoResultsMessage() {

    }
}
