package pl.conquerors.app.view.settings.password;

import android.icu.lang.UScript;
import android.text.TextUtils;
import android.util.Log;

import java.util.Objects;

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

        //check if old password valid
        if(TextUtils.isEmpty(oldPassword)){
            mView.showOldPasswordRequired();
            cancel = true;
        }
        //TODO get_password



        else if (!TextUtils.equals(oldPassword, "test123")){
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
            User user = SharedPreferenceUtil.getUser(mView.getContext());
            user.setmPassword(newPassword);
            UserEntity userEntity = new UserEntity(user);

            Call<UserEntity> call = RestClient.getInstance().updateUser(userEntity.getUserName(), userEntity);

            call.enqueue(new Callback<UserEntity>() {
                @Override
                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                    if(!response.isSuccessful()){
                        Log.e("change_password_code","Code: ".concat(String.valueOf(response.code())));
                        return;
                    }
                    User user = UserEntityMapper.transform(response.body());
                    SharedPreferenceUtil.setUser(mView.getContext(),user);
                    mView.onChangePasswordSucceeded();

                }

                @Override
                public void onFailure(Call<UserEntity> call, Throwable t) {
                    mView.setChangePasswordButtonEnabled(true);
                    Log.e("change_password", Objects.requireNonNull(t.getMessage()));
                }
            });
        }

    }
}
