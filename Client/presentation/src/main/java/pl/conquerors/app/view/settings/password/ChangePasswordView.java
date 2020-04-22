package pl.conquerors.app.view.settings.password;

import pl.conquerors.app.base.BaseView;

public interface ChangePasswordView extends BaseView {

    String getOldPassword();
    String getNewPassword();
    String getNewPasswordConfirmation();

    void setChangePasswordButtonEnabled(boolean enabled);

    //old password
    void showOldPasswordRequired();
    void showOldPasswordLengthInvalid(int min, int max);
    void showPasswordInvalid();
    void hideOldPasswordErrors();

    //new password
    void showNewPasswordRequired();
    void showNewPasswordLengthInvalid(int min, int max);
    void hideNewPasswordErrors();

    //new password confirmation
    void showNewPasswordConfirmationRequired();
    void showNewPasswordConfirmationLengthInvalid(int min, int max);
    void showPasswordDoNotMatch();
    void hideNewPasswordConfirmationErrors();

    void onChangePasswordSucceeded();
}
