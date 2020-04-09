package pl.conquerors.app.view.register;

import pl.conquerors.app.base.BaseView;

public interface RegistrationView extends BaseView {

    String getNick();
    String getEmail();
    String getPassword();
    String getPasswordConfirmation();
    String getBorn();

    void setRegistrationButtonEnabled(boolean enabled);

    //nick
    void showNickRequired();
    void showNickLengthInvalid(int min, int max);
    void hideNickError();

    //email
    void showEmailRequired();
    void showEmailLengthInvalid(int min, int max);
    void showEmailInvalid();
    void showEmailTaken();
    void hideEmailError();

    //password and password confirmation
    void showPasswordLengthInvalid(int min, int max);
    void showPasswordConfirmationLengthInvalid( int min, int max);
    void showPasswordsDoNotMatch();
    void hidePasswordError();
    void hidePasswordConfirmationError();

    //born
    void showBornRequired();
    void hideBornError();

    //callback
    void onRegistrationSucceeded(String email);
    void onRegistrationFiled();
    void showConfirmationView(String email);
}
