package pl.conquerors.app.view.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.home.HomeActivity;

public class SettingsActivity extends BaseActivity implements SettingsView {

    @BindView(R.id.settings_change_email)
    TextView mChangeEmail;

    @BindView(R.id.settings_change_password)
    TextView mChangePassword;

    @BindView(R.id.settings_remove_account)
    TextView mRemoveAccount;

    private SettingsPresenter mSettingsPresenter;

    public static Intent getStartingIntent(final Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSettingsPresenter = new SettingsPresenter();
        mSettingsPresenter.setmView(this);

        mSettingsPresenter.created();
    }

    @OnClick(R.id.settings_change_password)
    protected void onClickChangePassword() {
        mChangePassword.setBackgroundResource(R.color.theme_transparent_grey);
        showChangePassword();
    }

    @OnClick(R.id.settings_change_email)
    protected void onClickChangeEmail() {
        mChangeEmail.setBackgroundResource(R.color.theme_transparent_grey);
        showChangeEmail();
    }

    @OnClick(R.id.settings_remove_account)
    protected void onClickRemoveAccount() {
        mRemoveAccount.setBackgroundResource(R.color.theme_transparent_grey);
        showRemoveAccount();
    }

    @Override
    public void showChangeEmail() { Navigator.startChangeEmail(this); }

    private DialogInterface.OnClickListener getRemoveAccountPositiveListener() {
        return ((dialog, which) -> mSettingsPresenter.attemptRemoveAccount());
    }

    private void removeAccount(){
        DialogUtil.showSimpleDialog(
                SettingsActivity.this,
                getString(R.string.settings_action_remove_account),
                getString(R.string.settings_remove_dialog_message),
                getRemoveAccountPositiveListener(),
                null); // We don't need here anything more than default behaviour which is dismissing the dialog.

    }

    @Override
    public void showChangePassword() {
        mChangeEmail.setBackgroundResource(android.R.color.transparent);
        Navigator.startChangePassword(this);
    }

    @Override
    public void showRemoveAccount() {
        removeAccount();
    }

    @Override
    public void onRemoveAccountSucceeded() {
        SharedPreferenceUtil.setLoggedIn(this,false);
        SharedPreferenceUtil.setUserName(this, null);
        Toast.makeText(this, "Account deleted",Toast.LENGTH_SHORT);
        mRemoveAccount.setBackgroundResource(android.R.color.transparent);
        Navigator.startLoginWhenDeletedAccount(this);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSettingsPresenter.resume();
        mChangeEmail.setBackgroundResource(android.R.color.transparent);
        mRemoveAccount.setBackgroundResource(android.R.color.transparent);
        mChangePassword.setBackgroundResource(android.R.color.transparent);
    }

    @Override
    protected void onPause() {
        mSettingsPresenter.pause();
        super.onPause();
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }
}
