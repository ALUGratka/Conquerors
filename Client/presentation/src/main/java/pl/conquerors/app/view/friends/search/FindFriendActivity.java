package pl.conquerors.app.view.friends.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.User;

public class FindFriendActivity extends BaseActivity implements FindFriendView {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private SearchView searchView;

    private FindFriendPresenter findFriendPresenter;

    private FindFriendAdapter findFriendAdapter;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context,FindFriendActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        findFriendPresenter = new FindFriendPresenter();
        findFriendPresenter.setmView(this);
        findFriendPresenter.created();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        findFriendAdapter = new FindFriendAdapter();
        recyclerView.setAdapter(findFriendAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend_find, menu);

        SearchManager manager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.find_friends).getActionView();
        //searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                findFriendPresenter.attemptFindFriend();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void retryLoading(){ findFriendPresenter.attemptFindFriend(); }

    @Override
    public void setContentShown(final boolean visible) {
        recyclerView.setVisibility(visible ? View.VISIBLE: View.GONE);
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }


    @Override
    public String getSearchQuery() {
        return searchView.getQuery().toString();
    }

    @Override
    public void showSearchResult(List<User> result) {
        findFriendAdapter.setItems(result);
    }

    @Override
    public void showNoResultsMessage() {
        Toast.makeText(this, R.string.error_find_friend_no_results, Toast.LENGTH_SHORT).show();
    }
}
