package pl.conquerors.app.rest;

import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestService {

    @POST("register")
    Call<UserEntity> register(@Body UserEntity userEntity);
   /*
            @Field("email") final String email,
            @Field("username") final String username,
            @Field("password") final String password,
            @Field("birthDate") final String born
    );*/

    @POST("login")
    Call<UserEntity> login(@Body UserEntity userEntity);

    @POST("create-character")
    Call<CharacterEntity> createCharacter(@Body CharacterEntity characterEntity);

}
