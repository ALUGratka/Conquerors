package pl.conquerors.app.view.settings.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.method.PasswordTransformationMethod;
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

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordView {

    private ChangePasswordPresenter mChangePasswordPresenter;

    @BindView(R.id.change_password_form)
    View mChangePasswordForm;

    @BindView(R.id.current_password_input)
    TextInputLayout mCurrentPasswordInput;

    @BindView(R.id.current_password)
    TextInputEditText mCurrentPasswordView;

    @BindView(R.id.new_password_input)
    TextInputLayout mNewPasswordInput;

    @BindView(R.id.new_password)
    TextInputEditText mNewPasswordView;

    @BindView(R.id.new_password_confirm_input)
    TextInputLayout mNewPasswordConfirmationInput;

    @BindView(R.id.new_password_confirm)
    TextInputEditText mNewPasswordConfirmationView;

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

        setPrivacyStyle();

        mChangePasswordPresenter = new ChangePasswordPresenter();
        mChangePasswordPresenter.setmView(this);

        mChangePasswordPresenter.created();
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public String getOldPassword() { return mCurrentPasswordView.getText().toString(); }

    @Override
    public String getNewPassword() { return mNewPasswordView.getText().toString(); }

    @Override
    public String getNewPasswordConfirmation() { return mNewPasswordConfirmationView.getText().toString(); }

    @Override
    public void setChangePasswordButtonEnabled(boolean enabled) { mChangePasswordButton.setEnabled(enabled); }

    @Override
    public void showOldPasswordRequired() {
        mCurrentPasswordInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showOldPasswordLengthInvalid(int min, int max) {
        mCurrentPasswordInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_password),min, max));
    }

    @Override
    public void showPasswordInvalid() { mCurrentPasswordInput.setError(getString(R.string.error_incorrect_password)); }

    @Override
    public void hideOldPasswordErrors() {
        mCurrentPasswordInput.setError(null);
        mCurrentPasswordInput.setErrorEnabled(true);
    }

    @Override
    public void showNewPasswordRequired() { mNewPasswordInput.setError(getString(R.string.error_field_required));}

    @Override
    public void showNewPasswordLengthInvalid(int min, int max) {
        mNewPasswordInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_password),min, max));
    }

    @Override
    public void hideNewPasswordErrors() {
        mNewPasswordInput.setError(null);
        mNewPasswordInput.setErrorEnabled(true);
    }

    @Override
    public void showNewPasswordConfirmationRequired() { mNewPasswordConfirmationInput.setError(getString(R.string.error_field_required));}

    @Override
    public void showNewPasswordConfirmationLengthInvalid(int min, int max) {
        mNewPasswordConfirmationInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_password_confirm),min, max));
    }

    @Override
    public void showPasswordDoNotMatch() {
        mNewPasswordInput.setError(getString(R.string.error_invalid_password_match));
        mNewPasswordConfirmationInput.setError(getString(R.string.error_invalid_password_match));
    }

    @Override
    public void hideNewPasswordConfirmationErrors() {
        mNewPasswordConfirmationInput.setError(null);
        mNewPasswordConfirmationInput.setErrorEnabled(true);
    }

    @Override
    public void onChangePasswordSucceeded() {
        Toast.makeText(this, getString(R.string.change_password_success),Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setPrivacyStyle() {
        mCurrentPasswordView.setTransformationMethod(new PasswordTransformationMethod());
        mNewPasswordView.setTransformationMethod(new PasswordTransformationMethod());
        mNewPasswordConfirmationView.setTransformationMethod(new PasswordTransformationMethod());
    }
}
