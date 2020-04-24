package pl.conquerors.app.view.profile.my;


import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class MyProfilePresenter extends BasePresenter<MyProfileView> {

    public void getProfileName() {
        //TODO GET method to get profile data
        mView.setUserName(SharedPreferenceUtil.getUserName(mView.getContext()));

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
