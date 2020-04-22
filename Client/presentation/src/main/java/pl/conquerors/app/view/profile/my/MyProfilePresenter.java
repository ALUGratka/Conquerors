package pl.conquerors.app.view.profile.my;

import android.content.SharedPreferences;
import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.mapper.UserEntityMapper;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfilePresenter extends BasePresenter<MyProfileView> {

    public void getProfileName() {
        //TODO GET method to get profile data
        mView.setUserName(SharedPreferenceUtil.getUser(mView.getContext()).getmNick());

        /*Call<UserEntity> call = RestClient.getInstance().getMyProfile(SharedPreferenceUtil.getUser(mView.getContext()).getmEmail());

        call.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if(!response.isSuccessful()){
                    Log.e("get_profile", "Code: ".concat(String.valueOf(response.code())));
                    Log.e("get_profile", "Response: ".concat(response.body().getEmail()));

                    return;
                }
                User user = UserEntityMapper.transform(response.body());
                SharedPreferenceUtil.setUser(mView.getContext(),user);

                mView.setUserName(SharedPreferenceUtil.getUser(mView.getContext()).getmNick());
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.e("get_profile", t.getMessage());
            }
        });*/
    }

    public void setupEditMenu() {
        if (SharedPreferenceUtil.getUserName(mView.getContext())!=null) {
            mView.showEditButton();
        } else {
            mView.hideEditButton();
        }
    }

}
