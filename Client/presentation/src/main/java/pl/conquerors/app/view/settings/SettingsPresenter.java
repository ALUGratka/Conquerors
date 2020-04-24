package pl.conquerors.app.view.settings;

import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.UserGetEntity;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsPresenter extends BasePresenter<SettingsView> {

    public void attemptRemoveAccount(){
        mView.onRemoveAccountSucceeded();
        Call<UserEntity> call = RestClient.getInstance().deleteUser(new UserGetEntity("ala123@gmail.com"));


        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(!response.isSuccessful()){
                    Log.e("delete_user_code:", "Code: ".concat(String.valueOf(response.code())));
                    return;
                }
                Log.e("delete_user_response:",response.body().toString());
                mView.onRemoveAccountSucceeded();
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.e("delete_user_error: ", t.getMessage());
            }
        });
    }
}
