package pl.conquerors.app.rest;

import pl.conquerors.app.model.UserEntity;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestService {

    @FormUrlEncoded
    @POST("register")
    Call<UserEntity> register(
            @Field("email") final String email,
            @Field("username") final String username,
            @Field("password") final String password,
            @Field("birthDate") final String born
    );

    @FormUrlEncoded
    @POST("login")
    Call<UserEntity> login(
            @Field("username") final String username,
            @Field("password") final String password
    );



}
