package pl.conquerors.app.view.login;

import android.text.TextUtils;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.login.LoginUseCase;

public class LoginPresenter extends BasePresenter<LoginView> {

    LoginUseCase mLoginUseCase;

    public LoginPresenter(LoginUseCase loginUseCase) {
        mLoginUseCase = loginUseCase;
    }

    public void performLogin() {
        //disable button
        mView.setLoginButtonEnabled(false);

        //reset errors
        mView.hideNickError();
        mView.hidePasswordError();

        final String nick = mView.getNick();
        final String password = mView.getPassword();
        boolean cancel = false;

        if(TextUtils.isEmpty(nick)) {
            mView.showNickRequired();
            cancel = true;
        }

        if(TextUtils.isEmpty(password)) {
            mView.showPasswordRequired();
            cancel = true;
        } else if (!TextUtils.equals(password, "123")) {
            //TODO check if user in repository
            cancel = true;
        }

        if (cancel) {
            mView.setLoginButtonEnabled(true);
        } else {
            mView.showLoading();
            mView.onLoginSucceeded();
        }
    }
}
