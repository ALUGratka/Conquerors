package pl.conquerors.app.view.settings.email;

import android.text.TextUtils;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.util.Validator;

public class ChangeEmailPresenter extends BasePresenter<ChangeEmailView> {

    private final static int EMAIL_MIN = 5;
    private final static int EMAIL_MAX = 50;

    public void attemptToChangePassword(){
        mView.setChangeEmailButtonEnabled(false);

        mView.hideEmailError();
        mView.hideEmailConfirmationError();

        final String email = mView.getEmail();
        final String confirmation = mView.getEmailConfirmation();
        boolean cancel = false;

        //check if email valid
        if(TextUtils.isEmpty(email)) {
            mView.showEmailRequired();
            cancel = true;
        }else if(!Validator.isEmailValid(email)) {
            mView.showEmailInvalid();
            cancel = true;
        }else if (!Validator.isLengthValid(email, EMAIL_MIN, EMAIL_MAX)) {
            mView.showEmailLengthInvalid(EMAIL_MIN, EMAIL_MAX);
            cancel = true;
        }

        //check if confirmation valid
        if(TextUtils.isEmpty(confirmation)) {
            mView.showEmailRequired();
            cancel = true;
        }else if(!Validator.isEmailValid(confirmation)) {
            mView.showEmailInvalid();
            cancel = true;
        }else if (!Validator.isLengthValid(confirmation, EMAIL_MIN, EMAIL_MAX)) {
            mView.showEmailLengthInvalid(EMAIL_MIN, EMAIL_MAX);
            cancel = true;
        } else if(!TextUtils.equals(email,confirmation)){
            mView.showEmailsDoNotMatch();
            cancel = true;
        }

        if(cancel) {
            mView.setChangeEmailButtonEnabled(true);
        } else {

        }

    }
}
