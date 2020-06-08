package pl.conquerors.app.rest;

import java.util.List;

import pl.conquerors.app.domain.model.Gameplay;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.CharacterStatisticsEntity;
import pl.conquerors.app.model.EnemiesAchievementEntity;
import pl.conquerors.app.model.EnemyEntity;
import pl.conquerors.app.model.GameplayEntity;
import pl.conquerors.app.model.PrizeAnswerEntity;
import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.model.TreasureEntity;
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

    @PUT("gameplay-treasures-achievement")
    Call<TreasureAchievementEntity> updateTreasuresAchievement
            (@Body TreasureAchievementEntity treasureAchievementEntity);

    @PUT("gameplay-enemies-achievement")
    Call<EnemiesAchievementEntity> updateEnemiesAchievement
            (@Body EnemiesAchievementEntity enemiesAchievementEntity);

    @POST("create-character")
    Call<CharacterEntity> createCharacter(@Body CharacterEntity characterEntity);

    @POST("prize")
    Call<PrizeAnswerEntity> createPrizeDate(@Body PrizeDateEntity prizeDateEntity);

    @GET("gameplay")
    Call<GameplayEntity> gameplay(@Query("id") final long id);

    @POST("gameplay")
    Call<GameplayEntity> createGameplay
            (@Body GameplayEntity gameplayEntity);

    @GET("gameplays")
    Call<List<GameplayEntity>> getGameplays
            (@Query("player1id") final long player1id);

    @POST("gameplay-treasures-achievement")
    Call<TreasureAchievementEntity> createTreasuresAchievement
            (@Body TreasureAchievementEntity treasureAchievementEntity);

    @POST("gameplay-enemies-achievement")
    Call<EnemiesAchievementEntity> createEnemiesAchievement
            (@Body EnemiesAchievementEntity enemiesAchievementEntity);

    @GET("treasures")
    Call<List<TreasureEntity>> getTreasureEntity();

    @GET("enemies")
    Call<List<EnemyEntity>> getEnemyEntity();

    @GET("users/{userId}/prizes")
    Call<List<PrizeDateEntity>> getPrizeDate(@Path("userId") int user);

    @GET("users/{userId}/characters")
    Call<List<CharacterEntity>> getCharacter(@Path("userId") int user);

    @GET("/characters/{characterId}/statistic")
    Call<CharacterEntity> getCharacterStatistic(@Path("characterId") int character);

    @GET("/gameplay-treasures-achievement")
    Call<List<TreasureAchievementEntity>> getTreasuresAchievement(@Query("gameplayId") int gamePlayId);

    @GET("/gameplay-enemies-achievement")
    Call<List<EnemiesAchievementEntity>> getEnemiesAchievement(@Query("gameplayId") int gamePlayId);

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
