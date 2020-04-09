package pl.conquerors.app.view.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.login.LoginUseCase;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.login_form)
    View mLoginForm;

    @BindView(R.id.nick)
    AutoCompleteTextView mNickView;

    @BindView(R.id.nick_input)
    TextInputLayout mNickInput;

    @BindView(R.id.password)
    AutoCompleteTextView mPasswordView;

    @BindView(R.id.password_input)
    TextInputLayout mPasswordInput;

    @BindView(R.id.login_button)
    Button mLoginButton;

    @BindView(R.id.link_sign_up)
    TextView mSignUpLink;

    @BindView(R.id.progress)
    View mProgressView;

    LoginPresenter mLoginPresenter;

    public static Intent getStartingIntents(Context context){
        return new Intent(context, LoginActivity.class);
    }

    @OnTouch(R.id.login_form)
    protected boolean onLoginFormTouch () {
        hideKeyboard(mLoginForm);
        return true;
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        mLoginPresenter.performLogin();
    }

    @OnClick(R.id.link_sign_up)
    protected void onSignUpLinkClicked(){
        Navigator.startRegistration(this);
    }

    @Override
    public String getNick() {
        return mNickView.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void setLoginButtonEnabled(boolean enabled) {
        mLoginButton.setEnabled(enabled);
    }

    @Override
    public void showNickRequired() {
        mNickInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void hideNickError() {
        mNickInput.setError(null);
        mNickInput.setErrorEnabled(true);
    }

    @Override
    public void showPasswordRequired() {
        mPasswordInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void hidePasswordError() {
        mPasswordInput.setError(null);
        mPasswordInput.setErrorEnabled(true);
    }

    @Override
    public void showNoUser() {
        mPasswordInput.setError(getString(R.string.error_incorrect_password));
    }

    @Override
    public void onLoginSucceeded(/*User user*/) {
        Toast.makeText(this, getString(R.string.info_login_success, getNick()), Toast.LENGTH_SHORT).show();
        finish();
        Navigator.startHome(this);
    }

    @Override
    public void showConfirmationView(String nick) {
        Navigator.startHome(this);
    }

    @Override
    public void showPasswordInvalid() {
        mPasswordInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showLoading() {
        showProgress(true);
    }

    @Override
    public void hideLoading() {
        showProgress(false);
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginForm.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginUseCase loginUseCase = new LoginUseCase(new AndroidComposedScheduler());

        mLoginPresenter = new LoginPresenter(loginUseCase);
        mLoginPresenter.setmView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoginPresenter.pause();
    }
}
