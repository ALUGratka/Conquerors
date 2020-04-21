package pl.conquerors.app.view.settings.email;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;
import pl.conquerors.app.view.home.HomeActivity;

public class ChangeEmailActivity extends BaseActivity implements ChangeEmailView {


    public static Intent getStartingIntent(Context context) {
        return new Intent(context, ChangeEmailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }


}
