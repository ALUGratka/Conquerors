package pl.conquerors.app.rest;

import java.util.List;

import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.CharacterStatisticsEntity;
import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.model.UserEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface RestService {

    @POST("register")
    Call<UserEntity> register(@Body UserEntity userEntity);

    @POST("login")
    Call<UserEntity> login(@Body UserEntity userEntity);

    @GET("/user")
    Call<UserEntity> getMyProfile(@Query("username") String username);

    @DELETE("/user")
    Call<String> deleteUser(@Query("email") String email);

    @PUT("/user")
    Call<UserEntity> updateUser(@Query("username") String username,
                                @Body UserEntity userEntity);

    @POST("create-character")
    Call<CharacterEntity> createCharacter(@Body CharacterEntity characterEntity);

    @POST("prize")
    Call<PrizeDateEntity> createPrizeDate(@Body PrizeDateEntity prizeDateEntity);

    @GET("users/{userId}/prizes")
    Call<List<PrizeDateEntity>> getPrizeDate(@Path("userId") int user);

    @GET("users/{userId}/characters")
    Call<List<CharacterEntity>> getCharacter(@Path("userId") int user);

    @GET("/characters/{characterId}/statistic")
    Call<CharacterStatisticsEntity> getCharacterStatistic(@Path("characterId") int character);

    @GET("users/{userId}")
    Call<UserEntity> getFriend(
            @Path("userId") long userId
    );

    @GET("users/{userId}/friends")
    Call<List<UserEntity>> getAllFriends(@Path("userId") long user);

    @FormUrlEncoded
    @POST("users")
    Call<List<UserEntity>> findFriends(
            @Field("phrase") final String query
    );

    @FormUrlEncoded
    @POST("users")
    Call<Void> addFriend(
            @Field("userId") final long userId
    );

    @FormUrlEncoded
    @POST("users")
    Call<Void> acceptFriend(
            @Field("userId") final long userId
    );

    @FormUrlEncoded
    @POST("users")
    Call<Void> deleteFriend(
            @Field("userId") final long userId
    );

    @FormUrlEncoded
    @POST("users")
    Call<Void> ignoreFriend(
            @Field("userId") final long userId
    );

}
