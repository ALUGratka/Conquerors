package pl.conquerors.app.view.login;

import pl.conquerors.app.base.BaseView;
import pl.conquerors.app.domain.model.User;

public interface LoginView extends BaseView {

    String getNick();
    String getPassword();

    void setLoginButtonEnabled(boolean enabled);

    // nick || email
    void showNickRequired();
    void hideNickError();

    // password
    void showPasswordRequired();
    void showPasswordInvalid();
    void hidePasswordError();

    void showNoUser();

    // callbacks
    void onLoginSucceeded(User user);

    void showConfirmationView(String nick);
}
