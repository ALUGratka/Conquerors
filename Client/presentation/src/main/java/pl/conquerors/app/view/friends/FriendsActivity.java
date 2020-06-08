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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.friends.search.FindFriendAdapter;

public class FriendsActivity extends BaseActivity implements FriendsView {

    private FindFriendAdapter findFriendAdapter;
    private FriendsAdapter friendsAdapter;
    private FriendsPresenter friendsPresenter;

    @BindView(R.id.invitationLayout)
    LinearLayout invitationsLayout;

    @BindView(R.id.friends_layout)
    ScrollView friendsLayout;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.recycler2)
    RecyclerView recyclerView2;

    @BindView(R.id.no_fiends_layout)
    LinearLayout noFriendsLayout;

    @OnClick(R.id.no_fiends_button)
    protected void onNoFriendsButtonClicked(){
        Navigator.startFindFriend(this);
        finish();
    };



    public static Intent getStartingIntent(Context context) {
        return new Intent(context, FriendsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        friendsAdapter = new FriendsAdapter();
        findFriendAdapter = new FindFriendAdapter();
        recyclerView.setAdapter(friendsAdapter);
        recyclerView2.setAdapter(findFriendAdapter);

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
        return SharedPreferenceUtil.getUser(this).getUserId();
    }

    @Override
    public void showInvitations(List<User>invitations) {
        findFriendAdapter.setItems(invitations);
    }

    @Override
    public void showFriends(List<User> friends) {
        friendsAdapter.setItems(friends);
    }

    @Override
    public void setInvitationVisible(boolean visible) {
        invitationsLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setFriendsVisible(boolean visible) {
        friendsLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setNoFriendsVisible(boolean visible) {
        noFriendsLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }


    @Override
    public void handleError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
