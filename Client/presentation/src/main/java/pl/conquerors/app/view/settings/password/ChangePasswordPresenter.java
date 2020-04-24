package pl.conquerors.app.view.settings.password;

import android.text.TextUtils;
import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.util.Validator;

public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {

    private final static int PASSWORD_MIN = 6;
    private final static int PASSWORD_MAX = 30;

    public void attemptToChangePassword(){
        mView.setChangePasswordButtonEnabled(false);

        mView.hideOldPasswordErrors();
        mView.hideNewPasswordErrors();
        mView.hideNewPasswordConfirmationErrors();

        final String oldPassword = mView.getOldPassword();
        final String newPassword = mView.getNewPassword();
        final String newPasswordConfirmation = mView.getNewPasswordConfirmation();
        boolean cancel = false;

        //Log.e("password:", SharedPreferenceUtil.getUser(mView.getContext()).getmPassword());

        //check if old password valid
        if(TextUtils.isEmpty(oldPassword)){
            mView.showOldPasswordRequired();
            cancel = true;
        }else if (!TextUtils.equals(oldPassword, "ala123")) {
            mView.showPasswordInvalid();
            cancel = true;
        }

        //check if new password valid
        if(TextUtils.isEmpty(newPassword)){
            mView.showNewPasswordRequired();
            cancel = true;
        }else if (!Validator.isLengthValid(newPassword, PASSWORD_MIN, PASSWORD_MAX)) {
            mView.showNewPasswordLengthInvalid(PASSWORD_MIN, PASSWORD_MAX);
            cancel = true;
        }

        //check if new password confirmation valid
        if(TextUtils.isEmpty(newPasswordConfirmation)) {
            mView.showNewPasswordConfirmationRequired();
            cancel = true;
        }else if (!Validator.isLengthValid(newPasswordConfirmation, PASSWORD_MIN, PASSWORD_MAX)) {
            mView.showNewPasswordConfirmationLengthInvalid(PASSWORD_MIN, PASSWORD_MAX);
            cancel = true;
        } else if(!TextUtils.equals(newPassword, newPasswordConfirmation)){
            mView.showPasswordDoNotMatch();
            cancel = true;
        }

        if(cancel) {
            mView.setChangePasswordButtonEnabled(true);
        }
        else {
            //TODO REST API update Password

            mView.onChangePasswordSucceeded();
        }

    }
}
