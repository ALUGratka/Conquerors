package pl.conquerors.app.view.register;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTouch;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.registration.RegistrationUseCase;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;
import pl.conquerors.app.util.DateUtil;
import pl.conquerors.app.util.DialogUtil;

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

    public static Intent getStartingIntents(Context context) {
        Intent startingIntent = new Intent(context, RegistrationActivity.class);
        return startingIntent;
    }

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
    public void setRegistrationButtonEnabled(boolean enabled) { mRegisterButton.setEnabled(enabled); }

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
    public void showEmailLengthInvalid(int min, int max) {
        mEmailInput.setError(getString(R.string.error_field_length, getString(R.string.prompt_email),min,max));
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
    public void showPasswordLengthInvalid(int min, int max) {
        mPasswordInput.setError(getString(R.string.error_field_length,getString(R.string.prompt_password), min, max));
    }

    @Override
    public void showPasswordConfirmationLengthInvalid(int min, int max) {
        mPasswordConfirmationInput.setError(getString(R.string.error_field_length,getString(R.string.prompt_password_confirm), min, max));
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
    public void onRegistrationSucceeded(String email) {
        showConfirmationView(email);
    }

    @Override
    public void showConfirmationView(String email) {
        //TODO resultOfRegistration
        Toast.makeText(this,getString(R.string.info_registration_success, getNick()), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void setRegisterButtonEnabled(boolean enabled) {
        mRegisterButton.setEnabled(enabled);
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
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mPasswordView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @OnTouch(R.id.login_form)
    public boolean onLoginLayoutTouch(){
        hideKeyboard(mLoginFormView);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final RegistrationUseCase mRegistrationUseCase = new RegistrationUseCase(new AndroidComposedScheduler());

        mRegistrationPresenter = new RegistrationPresenter(mRegistrationUseCase);
        mRegistrationPresenter.setmView(this);

        setUpActionBar();
    }

    private void setUpActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnFocusChange(R.id.born)
    protected void onBornFocused(final View view, final boolean hasFocus){
        if(hasFocus) {
            DialogUtil.showDatePicker(RegistrationActivity.this, new Date(), new DialogUtil.OnDateSetListener() {
                @Override
                public void onDateSet(Date date) {
                    ((TextView)view).setText(DateUtil.getDateDottedString(date));
                }
            });
        }
    }

    @OnTouch(R.id.born)
    protected boolean onBornTouch (final View view, final MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
            DialogUtil.showDatePicker(RegistrationActivity.this, new Date(), new DialogUtil.OnDateSetListener() {
                @Override
                public void onDateSet(Date date) {
                    ((TextView)view).setText(DateUtil.getDateDottedString(date));
                }
            });
            return true;
        }
        return false;
    }

    @OnCheckedChanged(R.id.rules_check_box)
    protected void onRulesCheckedChanged(boolean isChecked) {
        mRegisterButton.setEnabled(isChecked);
    }

    @OnClick(R.id.register_button)
    public void onRegisterButtonClicked() {
        mRegistrationPresenter.performRegistration();
    }

}
