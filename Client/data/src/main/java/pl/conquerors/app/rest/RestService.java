package pl.conquerors.app.rest;

import pl.conquerors.app.model.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @GET("user")
    Call<UserEntity> getMyProfile(@Body String email);

    @DELETE("user/{email}")
    Call<String> deleteUser(@Path("email") String email);

    @PUT("user/{id}")
    Call<UserEntity> updateUser(@Path("id")int id,
                                @Body UserEntity userEntity);

}
