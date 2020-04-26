package pl.conquerors.app.rest;

import pl.conquerors.app.model.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RestService {

    @POST("register")
    Call<UserEntity> register(@Body UserEntity userEntity);

    @POST("login")
    Call<UserEntity> login(@Body UserEntity userEntity);

    @GET("/user")
    Call<UserEntity> getMyProfile(@Query("username") String username);

    @DELETE("/user")
    Call<String> deleteUser(@Query("email") String email);

    @PUT("user")
    Call<UserEntity> updateUser(@Body UserEntity userEntity);

}
