package pl.conquerors.app.view.register;

import pl.conquerors.app.base.BaseView;

public interface RegistrationView extends BaseView {

    String getNick();
    String getEmail();
    String getPassword();
    String getPasswordConfirmation();

    void setRegistrationButtonEnabled(boolean enabled);

    //nick
    void showNickRequired();
    void showNickLengthInvalid(int min, int max);
    void hideNickError();

    //email
    void showEmailRequired();
    void showEmailLengthInvalid(int min, int max);
    void showEmailInvalid();
    void hideEmailError();

    //password and password confirmation
    void showPasswordLengthInvalid(int min, int max);
    void showPasswordConfirmationLengthInvalid( int min, int max);
    void showPasswordsDoNotMatch();
    void hidePasswordError();
    void hidePasswordConfirmationError();

    //callback
    void onRegistrationSucceeded(String email);

    void showConfirmationView(String email);

    void setRegisterButtonEnabled(boolean b);
}
