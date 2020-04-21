package pl.conquerors.app.rest;

import pl.conquerors.app.model.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestService {

    @POST("register")
    Call<UserEntity> register(@Body UserEntity userEntity);

    @POST("login")
    Call<UserEntity> login(@Body UserEntity userEntity);

    @GET("users/{id}")
    Call<UserEntity> getMyProfile(@Path("id") int userId);

    @DELETE("userName")
    Call<UserEntity> deleteProfile(@Path("userName") String email);

}
