package pl.conquerors.app.navigation;

import android.content.Context;
import android.content.Intent;

import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.view.characterStatistics.CharacterStatisticsActivity;
import pl.conquerors.app.view.createCharacter.CreateCharacterActivity;
import pl.conquerors.app.view.createGame.chooseCharacter.ChooseCharacterActivity;
import pl.conquerors.app.view.createGame.chooseOpponent.ChooseOpponentActivity;
import pl.conquerors.app.view.everydayPrize.EverydayPrizeActivity;
import pl.conquerors.app.view.friends.FriendsActivity;
import pl.conquerors.app.view.friends.profile.FriendProfileActivity;
import pl.conquerors.app.view.friends.search.FindFriendActivity;
import pl.conquerors.app.view.gameplay.GameActivity;
import pl.conquerors.app.view.home.HomeActivity;
import pl.conquerors.app.view.login.LoginActivity;
import pl.conquerors.app.view.profile.my.MyProfileActivity;
import pl.conquerors.app.view.register.RegistrationActivity;
import pl.conquerors.app.view.settings.SettingsActivity;
import pl.conquerors.app.view.settings.email.ChangeEmailActivity;
import pl.conquerors.app.view.settings.password.ChangePasswordActivity;
import pl.conquerors.app.view.showGames.showGamesActivity;
import pl.conquerors.app.view.showCharacters.ShowCharactersActivity;

public class Navigator {

    public static void startHome(final Context context){
        context.startActivity(HomeActivity.getStartingIntents(context));
    }

    public static void startRegistration(final Context context){
        context.startActivity(RegistrationActivity.getStartingIntents(context));
    }

    public static void startLogin(Context context) {
        context.startActivity(LoginActivity.getStartingIntents(context));
    }

    public static void startLoginWhenDeletedAccount(Context context) {
        Intent intent = LoginActivity.getStartingIntents(context);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startPrize(Context context) {
        context.startActivity(EverydayPrizeActivity.getStartingIntents(context));
    }

    public static void startCreateCharacter(Context context) {
        context.startActivity(CreateCharacterActivity.getStartingIntents(context));
    }

    public static void startMyProfile(Context context){
        context.startActivity(MyProfileActivity.getStartingIntents(context));
    }

    public static void startMyCharacters(Context context){
        context.startActivity(ShowCharactersActivity.getStartingIntents(context));
    }

    public static void startCharacterStatistics(Context context, int characterId) {
        context.startActivity(CharacterStatisticsActivity.getStartingIntents(context, characterId));
    }

    public static void startSettings(Context context) {
        context.startActivity(SettingsActivity.getStartingIntent(context));
    }

    public static void startChangeEmail(Context context) {
        context.startActivity(ChangeEmailActivity.getStartingIntent(context));
    }

    public static void startChangePassword(Context context) {
        context.startActivity(ChangePasswordActivity.getStartingIntent(context));
    }

    public static void startAddGame(Context context){
        context.startActivity(ChooseCharacterActivity.getStartingIntents(context));
    }

    public static void startChooseOpponent(Context context)
    {
        context.startActivity(ChooseOpponentActivity.getStartingIntents(context));
    }

    public static void startShowGames(Context context)
    {
        context.startActivity(showGamesActivity.getStartingIntents(context));
    }

    public static void startFriends(Context context) {
        context.startActivity(FriendsActivity.getStartingIntent(context));
    }

    public static void startFindFriend(Context context) {
        context.startActivity(FindFriendActivity.getStartingIntent(context));
    }

    public static void startFriendProfile(Context context, final User user) {
        context.startActivity(FriendProfileActivity.getStartingIntent(context, user));
    }

    public static void startGame(Context context){
        context.startActivity(GameActivity.getStartingIntents(context));
    }
}
