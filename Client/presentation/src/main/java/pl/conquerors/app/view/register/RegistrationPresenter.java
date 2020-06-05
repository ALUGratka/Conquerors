package pl.conquerors.app.view.register;

import android.text.TextUtils;
import android.util.Log;

import java.util.Date;
import java.util.Objects;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.registration.RegistrationUseCase;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.DateUtil;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.util.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RegistrationPresenter extends BasePresenter<RegistrationView> {

    private final static int NICK_MIN = 2;
    private final static int NICK_MAX = 20;
    private final static int PASSWORD_MIN = 6;
    private final static int PASSWORD_MAX = 30;
    private final static int EMAIL_MIN = 5;
    private final static int EMAIL_MAX = 50;

    private RegistrationUseCase mUseCase;

    RegistrationPresenter(final RegistrationUseCase useCase) { mUseCase = useCase; }

    void performRegistration() {
        mView.setRegistrationButtonEnabled(false);

        mView.hideNickError();
        mView.hideEmailError();
        mView.hidePasswordError();
        mView.hidePasswordConfirmationError();

        final String nick = mView.getNick();
        final String email = mView.getEmail();
        final String password = mView.getPassword();
        final String confirmation = mView.getPasswordConfirmation();
        boolean cancel = false;

        //check if nick is valid ->Validator class in data package
        if(TextUtils.isEmpty(nick)) {
            mView.showNickRequired();
            cancel = true;
        } else if (!Validator.isLengthValid(nick,NICK_MIN,NICK_MAX)){
            mView.showNickLengthInvalid(NICK_MIN, NICK_MAX);
            cancel = true;
        }

        //check if email is valid
        if(TextUtils.isEmpty(email)) {
            mView.showEmailRequired();
            cancel = true;
        }else if(!Validator.isEmailValid(email)) {
            mView.showEmailInvalid();
            cancel = true;
        }else if(!Validator.isLengthValid(email, EMAIL_MIN, EMAIL_MAX)) {
            mView.showEmailLengthInvalid(EMAIL_MIN, EMAIL_MAX);
            cancel = true;
        }

        //check if valid password && confirmation
        if(!Validator.isLengthValid(password,PASSWORD_MIN, PASSWORD_MAX)) {
            mView.showPasswordLengthInvalid(PASSWORD_MIN,PASSWORD_MAX);
            cancel = true;
            if(!Validator.isLengthValid(confirmation,PASSWORD_MIN,PASSWORD_MAX)) {
                mView.showPasswordConfirmationLengthInvalid(PASSWORD_MIN,PASSWORD_MAX);
            }
        }else if(!TextUtils.equals(password,confirmation)) {
            mView.showPasswordsDoNotMatch();
            cancel = true;
        }


        if(cancel) {
            mView.setRegistrationButtonEnabled(true);
        } else {
            mView.showLoading();

            mUseCase.setData(nick, email, password);

            Call<UserEntity> call = RestClient.getInstance().register(new UserEntity(email,nick,password));

            //TODO remove it when API changed
            User user = new User();
            user.setUserEmail(email);
            user.setUserNick(nick);
            user.setUserPassword(password);

            SharedPreferenceUtil.setUser(mView.getContext(),user);

            call.enqueue(new Callback<UserEntity>() {
                @Override
                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                    if(!response.isSuccessful()) {
                        Log.e("registration", "code: "+response.code());
                        return;
                    }
                    if(response.message().equals("User with given username already exists")) {
                        Log.e("registration", "User with given username already exists");
                        return;
                    }
                    mView.onRegistrationSucceeded(email);
                }

                @Override
                public void onFailure(Call<UserEntity> call, Throwable t) {
                    mView.hideLoading();
                    mView.setRegisterButtonEnabled(true);
                    Log.e("registration", Objects.requireNonNull(t.getMessage()));
                }
            });
        }

    }

}
