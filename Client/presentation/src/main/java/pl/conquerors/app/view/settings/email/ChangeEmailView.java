package pl.conquerors.app.view.settings.email;

import pl.conquerors.app.base.BaseView;

public interface ChangeEmailView extends BaseView {

    String getEmail();
    String getEmailConfirmation();

    void setChangeEmailButtonEnabled(boolean enabled);

    //email
    void showEmailRequired();
    void showEmailLengthInvalid(int min, int max);
    void showEmailInvalid();
    void hideEmailError();

    //email confirmation
    void showEmailConfirmationRequired();
    void showEmailConfirmationLengthInvalid(int min, int max);
    void showEmailsDoNotMatch();
    void hideEmailConfirmationError();

    void onChangeEmailSucceeded();
}
