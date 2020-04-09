package pl.conquerors.app.view.login;

import android.text.TextUtils;
import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.login.LoginUseCase;
import pl.conquerors.app.domain.interactor.profile.GetMyProfileUseCase;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.util.Validator;
import rx.Subscriber;

public class LoginPresenter extends BasePresenter<LoginView> {

    LoginUseCase mLoginUseCase;
    GetMyProfileUseCase mGetMyProfileUseCase;

    public LoginPresenter(LoginUseCase loginUseCase, GetMyProfileUseCase getMyProfileUseCase) {
        mLoginUseCase = loginUseCase;
        mGetMyProfileUseCase = getMyProfileUseCase;
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
        } else if (!Validator.isPasswordValid(password)) {
            mView.showPasswordInvalid();
            cancel = true;
        }

        if (cancel) {
            mView.setLoginButtonEnabled(true);
        } else {
            mView.showLoading();

            /*User user = UserUtil.checkIfUserLoginCorrect(nick,password);

            if(user!=null){
                mView.onLoginSucceeded(user);
            }
            else{
                mView.hideLoading();
                mView.setLoginButtonEnabled(true);
                handleError(new Throwable());
                Log.e("user: ", "Niepoprawne hasło");
            }*/


            handleSubscription(mGetMyProfileUseCase.execute().subscribe(new Subscriber<User>() {

                @Override
                public void onCompleted() {
                    mView.hideLoading();
                }

                @Override
                public void onError(final Throwable error) {
                    handleError(error);
                }

                @Override
                public void onNext(final User user) {
                    Log.e("user", user.toString());
                }
            }));


            /*mLoginUseCase.setCredentials(nick, password, FirebaseInstanceId.getInstance().getToken(), Locale.getDefault().getLanguage());*/
            /*handleSubscription(mLoginUseCase.execute().subscribe(new Subscriber<User>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(final Throwable error) {
                    mView.hideLoading();
                    mView.setLoginButtonEnabled(true);
                    handleError(error);
                }

                @Override
                public void onNext(final User user) {
                    mView.onLoginSucceeded(user);
                }
            }));*/

        }
    }
}
