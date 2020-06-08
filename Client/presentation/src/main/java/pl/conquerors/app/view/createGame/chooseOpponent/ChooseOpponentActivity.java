package pl.conquerors.app.view.createGame.chooseOpponent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.createGame.chooseCharacter.ChooseCharacterPresenter;

public class ChooseOpponentActivity extends BaseActivity implements ChooseOpponentView {

    private ChooseOpponentAdapter adapter;
    private ChooseOpponentPresenter presenter;

    private User opponent;

    @OnClick(R.id.backButton)
    public void onPreviousButtonClicker(){
        Navigator.startAddGame(this);
    }

    @OnClick(R.id.forthButton)
    public void onNextButtonClicker(){
        if(SharedPreferenceUtil.getOpponent(this) ==null) {
            Toast.makeText(this, getString(R.string.create_game_no_opponent_choose_error), Toast.LENGTH_SHORT).show();
        }
        else {
            //TODO send game info to database
            finish();
            Toast.makeText(this, getString(R.string.game_created), Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getStartingIntents(Context context){
        return new Intent(context, ChooseOpponentActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_choose_opponent);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ChooseOpponentAdapter();
        recyclerView.setAdapter(adapter);

        presenter = new ChooseOpponentPresenter();
        presenter.setmView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferenceUtil.setOpponent(this,null);
        Navigator.startAddGame(this);
    }

    @Override
    public Long getUserId() {
        return SharedPreferenceUtil.getUser(this).getUserId();
    }

    @Override
    public void showFriends(List<User> friends) {
        adapter.setItems(friends);
    }

    @Override
    public void setOpponent() {
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

}