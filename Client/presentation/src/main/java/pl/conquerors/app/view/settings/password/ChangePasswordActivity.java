package pl.conquerors.app.view.settings.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.settings.email.ChangeEmailView;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView {

    private ChangePasswordPresenter mChangePasswordPresenter;

    @BindView(R.id.change_password_form)
    View mChangePasswordForm;

    @BindView(R.id.current_password)
    TextInputLayout mCurrentPasswordView;

    @BindView(R.id.current_password_input)
    TextInputEditText mCurrentPasswordInput;

    @BindView(R.id.new_password)
    TextInputLayout mNewPasswordView;

    @BindView(R.id.new_password_input)
    TextInputEditText mNewPasswordInput;

    @BindView(R.id.new_password_confirm)
    TextInputLayout mNewPasswordConfirmationView;

    @BindView(R.id.new_password_confirm_input)
    TextInputEditText mNewPasswordConfirmationInput;

    @BindView(R.id.change_password_button)
    Button mChangePasswordButton;

    @OnTouch(R.id.change_password_form)
    protected boolean onPasswordFormTouch() {
        hideKeyboard(mChangePasswordForm);
        return true;
    }

    @OnClick(R.id.change_password_button)
    protected void onChangePasswordButtonClicked(){ mChangePasswordPresenter.attemptToChangePassword(); }

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

    @Override
    public String getOldPassword() { return mCurrentPasswordInput.getText().toString(); }

    @Override
    public String getNewPassword() { return mNewPasswordInput.getText().toString(); }

    @Override
    public String getNewPasswordConfirmation() { return mNewPasswordConfirmationInput.getText().toString(); }

    @Override
    public void setChangePasswordButtonEnabled(boolean enabled) { mChangePasswordButton.setEnabled(enabled); }

    @Override
    public void showOldPasswordRequired() {
        mCurrentPasswordView.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showOldPasswordLengthInvalid(int min, int max) {
        mCurrentPasswordView.setError(getString(R.string.error_field_length, getString(R.string.prompt_password),min, max));
    }

    @Override
    public void showPasswordInvalid() { mCurrentPasswordView.setError(getString(R.string.error_invalid_password)); }

    @Override
    public void hideOldPasswordErrors() {
        mCurrentPasswordView.setError(null);
        mCurrentPasswordView.setErrorEnabled(true);
    }

    @Override
    public void showNewPasswordRequired() { mNewPasswordView.setError(getString(R.string.error_field_required));}

    @Override
    public void showNewPasswordLengthInvalid(int min, int max) {
        mNewPasswordView.setError(getString(R.string.error_field_length, getString(R.string.prompt_password),min, max));
    }

    @Override
    public void hideNewPasswordErrors() {
        mNewPasswordView.setError(null);
        mNewPasswordView.setErrorEnabled(true);
    }

    @Override
    public void showNewPasswordConfirmationRequired() { mNewPasswordConfirmationView.setError(getString(R.string.error_field_required));}

    @Override
    public void showNewPasswordConfirmationLengthInvalid(int min, int max) {
        mNewPasswordConfirmationView.setError(getString(R.string.error_field_length, getString(R.string.prompt_password_confirm),min, max));
    }

    @Override
    public void showPasswordDoNotMatch() {
        mNewPasswordView.setError(getString(R.string.error_invalid_password_match));
        mNewPasswordConfirmationView.setError(getString(R.string.error_invalid_password_match));
    }

    @Override
    public void hideNewPasswordConfirmationErrors() {
        mNewPasswordConfirmationView.setError(null);
        mNewPasswordConfirmationView.setErrorEnabled(true);
    }

    @Override
    public void onChangePasswordSucceeded() {
        User user = SharedPreferenceUtil.getUser(this);
        user.setmPassword(getNewPassword());
        SharedPreferenceUtil.setUser(this, user);
        Toast.makeText(this, getString(R.string.change_password_success),Toast.LENGTH_SHORT).show();
        finish();
    }
}
