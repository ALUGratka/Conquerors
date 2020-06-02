package pl.conquerors.app.view.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class FriendsActivity extends BaseActivity implements FriendsView {

    private FriendsAdapter friendsAdapter;
    private FriendsPresenter friendsPresenter;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, FriendsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        friendsAdapter = new FriendsAdapter();
        recyclerView.setAdapter(friendsAdapter);

        friendsPresenter = new FriendsPresenter();
        friendsPresenter.setmView(this);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        getSupportActionBar().setElevation(0);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add_friend) {
            Navigator.startFindFriend(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        friendsPresenter.resume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public Long getUserId() {
        //return SharedPreferenceUtil.getUser(this).getmId();
        return Long.valueOf(1);
    }

    @Override
    public void showFriends(List<User> friends) {
        friendsAdapter.setItems(friends);
    }

    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
