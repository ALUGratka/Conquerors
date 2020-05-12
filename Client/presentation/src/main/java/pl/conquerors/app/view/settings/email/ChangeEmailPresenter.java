package pl.conquerors.app.view.settings.email;

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

public class ChangeEmailPresenter extends BasePresenter<ChangeEmailView> {

    private final static int EMAIL_MIN = 5;
    private final static int EMAIL_MAX = 50;

    public void attemptToChangePassword(){
        mView.setChangeEmailButtonEnabled(false);

        mView.hideEmailError();
        mView.hideEmailConfirmationError();

        final String email = mView.getEmail();
        final String confirmation = mView.getEmailConfirmation();
        boolean cancel = false;

        //check if email valid
        if(TextUtils.isEmpty(email)) {
            mView.showEmailRequired();
            cancel = true;
        }else if(!Validator.isEmailValid(email)) {
            mView.showEmailInvalid();
            cancel = true;
        }else if (!Validator.isLengthValid(email, EMAIL_MIN, EMAIL_MAX)) {
            mView.showEmailLengthInvalid(EMAIL_MIN, EMAIL_MAX);
            cancel = true;
        }

        //check if confirmation valid
        if(TextUtils.isEmpty(confirmation)) {
            mView.showEmailConfirmationRequired();
            cancel = true;
        }else if(!Validator.isEmailValid(confirmation)) {
            mView.showEmailInvalid();
            cancel = true;
        }else if (!Validator.isLengthValid(confirmation, EMAIL_MIN, EMAIL_MAX)) {
            mView.showEmailConfirmationLengthInvalid(EMAIL_MIN, EMAIL_MAX);
            cancel = true;
        } else if(!TextUtils.equals(email,confirmation)){
            mView.showEmailsDoNotMatch();
            cancel = true;
        }

        if(cancel) {
            mView.setChangeEmailButtonEnabled(true);
        } else {


            //TODO REST API update Email

            User user = SharedPreferenceUtil.getUser(mView.getContext());
            user.setmEmail(email);
            Log.e("old_user", user.getmPassword());

            UserEntity userEntity = new UserEntity(user);

            Call<UserEntity> call = RestClient.getInstance().updateUser(userEntity.getUserName(), userEntity);

            call.enqueue(new Callback<UserEntity>() {
                @Override
                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                    if(!response.isSuccessful()){
                        Log.e("update_user: ", "Code: ".concat(String.valueOf(response.code())).concat("Message: ").concat(response.message()));
                        return;
                    }
                    SharedPreferenceUtil.setUser(mView.getContext(),user);

                    Log.e("new_user", user.getmPassword());

                    mView.onChangeEmailSucceeded();
                }

                @Override
                public void onFailure(Call<UserEntity> call, Throwable t) {
                    mView.setChangeEmailButtonEnabled(true);
                    Log.e("update_user: ", Objects.requireNonNull(t.getMessage()));
                }
            });
        }

    }
}
