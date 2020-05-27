package pl.conquerors.app.view.fiends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;

public class FriendsActivity extends BaseActivity {


    public static Intent getStartingIntent(Context context) {
        return new Intent(context, FriendsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        getSupportActionBar().setElevation(0);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }
}
