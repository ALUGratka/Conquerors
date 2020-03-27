package pl.conquerors.app.view.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.registration.RegistrationUseCase;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;

public class RegistrationActivity extends BaseActivity implements RegistrationView {

    @BindView(R.id.nick)
    EditText mNickView;

    @BindView(R.id.nick_input)
    TextInputLayout mNickInput;

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.email_input)
    TextInputLayout mEmailInput;

    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.password_input)
    TextInputLayout mPasswordInput;

    @BindView(R.id.password_confirm)
    EditText mPasswordConfirmationView;

    @BindView(R.id.password_confirm_input)
    TextInputLayout mPasswordConfirmationInput;

    @BindView(R.id.born)
    EditText mBornView;

    @BindView(R.id.born_input)
    TextInputLayout mBornInput;

    @BindView(R.id.login_progress)
    View mProgressView;

    @BindView(R.id.login_form)
    View mLoginFormView;

    @BindView(R.id.register_button)
    Button mRegisterButton;

    private RegistrationPresenter mRegistrationPresenter;

    private PopupMenu mPopupMenu;

    public static Intent getStartingIntents(Context context){
        final Intent startingIntent = new Intent(context, RegistrationActivity.class);
        return startingIntent;
    }

    //TODO date picker
    @OnFocusChange(R.id.born)
    public void onBornFocused(final View view, final boolean hasFocus){
        if(hasFocus) {

        }
    }

    @OnClick(R.id.register_button)
    public void onClick(View view) throws ParseException {mRegistrationPresenter.performRegistration();}

    @OnCheckedChanged(R.id.rules_check_box)
    void rulesCheckChanged(boolean isChecked) {mRegisterButton.setEnabled(isChecked);}

    @Override
    public String getNick() { return mNickView.getText().toString(); }

    @Override
    public String getEmail() { return mEmailView.getText().toString(); }

    @Override
    public String getPassword() { return mPasswordView.getText().toString(); }

    @Override
    public String getPasswordConfirmation() { return mPasswordConfirmationView.getText().toString(); }

    @Override
    public String getBorn() { return mBornView.getText().toString(); }

    @Override
    public void setRegistrationButtonEnabled(boolean enabled) {
        mRegisterButton.setEnabled(enabled);
    }

    @Override
    public void showNickRequired() {
        mNickInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showNickLengthInvalid(int min, int max) {
        mNickInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_nick), min, max));
    }

    @Override
    public void hideNickError() {
        mNickInput.setError(null);
        mNickInput.setErrorEnabled(false);
    }

    @Override
    public void showEmailRequired() {
        mEmailInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void showEmailLengthInvalid(final int min, final int max) {
        mEmailInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_email), min, max));
    }

    @Override
    public void showEmailInvalid() {
        mEmailInput.setError(getString(R.string.error_invalid_email));
    }

    @Override
    public void hideEmailError() {
        mEmailInput.setError(null);
        mEmailInput.setErrorEnabled(false);
    }

    @Override
    public void showPasswordLengthInvalid(final int min, final int max) {
        mPasswordInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_password), min, max));
    }

    @Override
    public void showPasswordConfirmationLengthInvalid(final int min, final int max) {
        mPasswordConfirmationInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_password_confirm), min, max));
    }

    @Override
    public void showPasswordsDoNotMatch() {
        mPasswordInput.setError(getString(R.string.error_invalid_password));
        mPasswordConfirmationInput.setError(getString(R.string.error_invalid_password));
    }

    @Override
    public void hidePasswordError() {
        mPasswordInput.setError(null);
        mPasswordInput.setErrorEnabled(false);
    }

    @Override
    public void hidePasswordConfirmationError() {
        mPasswordConfirmationInput.setError(null);
        mPasswordConfirmationInput.setErrorEnabled(false);
    }

    @Override
    public void showBornRequired() {
        mBornInput.setError(getString(R.string.error_field_required));
    }

    @Override
    public void hideBornError() {
        mBornInput.setError(null);
        mBornInput.setErrorEnabled(false);
    }

    @Override
    public void onRegistrationSucceeded(final String email) {
        // after successful registration, show confirmation screen
        showConfirmationView(email);
    }

    @Override
    public void showConfirmationView(final String email) {
        //TODO resultOfRegistration
        Navigator.startHome(this);
        //Navigator.startConfirmRegistrationForResult(this, email, REQUEST_CODE_CONFIRMATION);
    }

    @Override
    public void showLoading() { showProgress(true); }

    @Override
    public void hideLoading() { showProgress(false); }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final RegistrationUseCase useCase = new RegistrationUseCase(new AndroidComposedScheduler());

        mRegistrationPresenter = new RegistrationPresenter(useCase);
        mRegistrationPresenter.setmView(this);

        setupActionBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRegistrationPresenter.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRegistrationPresenter.resume();
    }
}
