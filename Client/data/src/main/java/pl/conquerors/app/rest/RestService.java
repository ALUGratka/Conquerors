package pl.conquerors.app.rest;

import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.UserGetEntity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestService {

    @POST("register")
    Call<UserEntity> register(@Body UserEntity userEntity);

    @POST("login")
    Call<UserEntity> login(@Body UserEntity userEntity);

    @GET("user/{username}")
    Call<UserEntity> getMyProfile(@Path("username") String username);

    @HTTP(method = "DELETE", path = "user", hasBody = true)
    Call<UserEntity> deleteUser(@Body UserGetEntity entity);

    @PUT("user")
    Call<UserEntity> updateUser(@Body UserEntity userEntity);

}
