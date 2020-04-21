package pl.conquerors.app.view.settings;

import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsPresenter extends BasePresenter<SettingsView> {

    public void attemptRemoveAccount(){
        mView.onRemoveAccountSucceeded();
        /*Call<UserEntity> call = RestClient.getInstance().deleteProfile(SharedPreferenceUtil.getUserName(mView.getContext()));

        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(!response.isSuccessful()){
                    Log.e("delete_user:", "Code: ".concat(String.valueOf(response.code())));
                    return;
                }
                mView.onRemoveAccountSucceeded();
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.e("delete_user: ", t.getMessage());
            }
        });*/
    }
}
