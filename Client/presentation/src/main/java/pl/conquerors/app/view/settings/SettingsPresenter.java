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
        //Call<String> call = RestClient.getInstance().deleteUser(SharedPreferenceUtil.getUser(mView.getContext()).getmEmail());
        Call<String> call = RestClient.getInstance().deleteUser("ala123@gmail.com");


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.e("delete_user:", "Code: ".concat(String.valueOf(response.code())));
                    return;
                }
                Log.e("delete_user:",response.body());
                mView.onRemoveAccountSucceeded();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("delete_user: ", t.getMessage());
            }
        });
    }
}
