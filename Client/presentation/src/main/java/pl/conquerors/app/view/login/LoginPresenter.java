package pl.conquerors.app.view.login;

import android.text.TextUtils;
import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.util.Validator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<LoginView> {

    //SessionRepository sessionRepository;


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

            Call<UserEntity> call = RestClient.getInstance().login(new UserEntity(nick,password));

            call.enqueue(new Callback<UserEntity>() {
                @Override
                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                    if(!response.isSuccessful()){
                        Log.e("login", "Code: "+response.code());
                        return;
                    }
                    User user = UserEntityMapper.transform(response.body());
                    SharedPreferenceUtil.setLoggedIn(mView.getContext(), true);

                    Log.e("session", "Is logged: ".concat(String.valueOf(SharedPreferenceUtil.getLoggedStatus(mView.getContext()))));

                    mView.onLoginSucceeded();
                }

                @Override
                public void onFailure(Call<UserEntity> call, Throwable t) {
                    mView.hideLoading();
                    mView.setLoginButtonEnabled(true);
                    Log.e("login", t.getMessage());
                }
            });
        }
    }
}
