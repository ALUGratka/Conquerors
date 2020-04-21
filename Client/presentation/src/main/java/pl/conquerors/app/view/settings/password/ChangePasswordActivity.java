package pl.conquerors.app.view.settings.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.view.settings.email.ChangeEmailView;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView {


    private ChangePasswordPresenter mChangePasswordPresenter;

    static public Intent getStartingIntent(Context context){
        return new Intent(context, ChangePasswordActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mChangePasswordPresenter = new ChangePasswordPresenter();
        mChangePasswordPresenter.setmView(this);

        mChangePasswordPresenter.created();
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }
}
