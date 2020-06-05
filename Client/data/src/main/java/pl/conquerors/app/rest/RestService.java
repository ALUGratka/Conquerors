package pl.conquerors.app.rest;

import java.util.List;

import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.CharacterStatisticsEntity;
import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.model.UserEntity;
import pl.conquerors.app.model.UserRelationshipEntity;
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

    @GET("/getAllFriends")
    Call<List<UserEntity>> getAllFriends(
            @Query("userId") final long user);

    @GET("/getAllInvitations")
    Call<List<UserEntity>> getAllInvitations(
            @Query("userId") final long user);

    @GET("/findUsers")
    Call<List<UserEntity>> findFriends(
            @Query("phrase") final String query,
            @Query("userId") final long userId
    );

    @GET("/relationshipStatus")
    Call<UserRelationshipEntity> checkUsersRelationship(
            @Query("user1ID") final long user1Id,
            @Query("user2ID") final long user2Id
    );

    @PUT("/addFriend")
    Call<UserRelationshipEntity> addFriend(
            @Body UserRelationshipEntity userRelationshipEntity
    );

    @PUT("/deleteFriend")
    Call<UserRelationshipEntity> deleteFriend(
            @Body UserRelationshipEntity userRelationshipEntity
    );

    @PUT("/acceptFriend")
    Call<UserRelationshipEntity> acceptFriend(
            @Body UserRelationshipEntity userRelationshipEntity
    );

}
