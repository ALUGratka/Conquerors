package pl.conquerors.app.view.settings.email;

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

public class ChangeEmailActivity extends BaseActivity implements ChangeEmailView {

    ChangeEmailPresenter mChangeEmailPresenter;

    @BindView(R.id.change_email_form)
    View mChangeEmailLayout;

    @BindView(R.id.new_email)
    TextInputEditText mNewEmailView;

    @BindView(R.id.new_email_input)
    TextInputLayout mNewEmailInput;

    @BindView(R.id.new_email_confirm)
    TextInputEditText mNewEmailConfirmationView;

    @BindView(R.id.new_email_confirm_input)
    TextInputLayout mNewEmailConfirmationInput;

    @BindView(R.id.change_email_button)
    Button mChangeButton;

    @OnTouch(R.id.change_email_form)
    protected boolean onLoginFormTouch () {
        hideKeyboard(mChangeEmailLayout);
        return true;
    }

    @OnClick(R.id.change_email_button)
    protected void onChangeEmailButtonClick() {
        mChangeEmailPresenter.attemptToChangePassword();
    }

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, ChangeEmailActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        mChangeEmailPresenter = new ChangeEmailPresenter();

        mChangeEmailPresenter.setmView(this);
        mChangeEmailPresenter.created();
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }


    @Override
    public String getEmail() { return mNewEmailView.getText().toString(); }

    @Override
    public String getEmailConfirmation() { return mNewEmailConfirmationView.getText().toString(); }

    @Override
    public void setChangeEmailButtonEnabled(boolean enabled) { mChangeButton.setEnabled(enabled); }

    @Override
    public void showEmailRequired() { mNewEmailInput.setError(getString(R.string.error_field_required)); }

    @Override
    public void showEmailLengthInvalid(int min, int max) {
        mNewEmailInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_email),min, max));
    }

    @Override
    public void showEmailInvalid() { mNewEmailInput.setError(getString(R.string.error_invalid_email)); }

    @Override
    public void hideEmailError() {
        mNewEmailInput.setError(null);
        mNewEmailInput.setErrorEnabled(true);
    }

    @Override
    public void showEmailConfirmationRequired() { mNewEmailConfirmationInput.setError(getString(R.string.error_field_required)); }

    @Override
    public void showEmailConfirmationLengthInvalid(int min, int max) {
        mNewEmailConfirmationInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_email),min, max));
    }

    @Override
    public void showEmailsDoNotMatch() {
        mNewEmailInput.setError(getString(R.string.error_invalid_email_match));
        mNewEmailConfirmationInput.setError(getString(R.string.error_invalid_email_match));
    }

    @Override
    public void hideEmailConfirmationError() {
        mNewEmailConfirmationInput.setError(null);
        mNewEmailConfirmationInput.setErrorEnabled(true);
    }

    @Override
    public void onChangeEmailSucceeded() {
        User user = SharedPreferenceUtil.getUser(this);
        user.setmEmail(getEmail());
        SharedPreferenceUtil.setUser(this, user);
        Toast.makeText(this, getString(R.string.change_email_success),Toast.LENGTH_SHORT).show();
        finish();
    }
}
