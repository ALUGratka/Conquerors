package pl.conquerors.app.navigation;

import android.content.Context;

import pl.conquerors.app.view.createCharacter.CreateCharacterAppearanceActivity;
import pl.conquerors.app.view.createCharacter.CreateCharacterClassActivity;
import pl.conquerors.app.view.createCharacter.CreateCharacterNameActivity;
import pl.conquerors.app.view.createCharacter.CreateCharacterSexActivity;
import pl.conquerors.app.view.createCharacter.CreateCharacterSummaryActivity;
import pl.conquerors.app.view.everydayPrize.EverydayPrizeActivity;
import pl.conquerors.app.view.home.HomeActivity;
import pl.conquerors.app.view.login.LoginActivity;
import pl.conquerors.app.view.profile.my.MyProfileActivity;
import pl.conquerors.app.view.register.RegistrationActivity;

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

    public static void startPrize(Context context) {
        context.startActivity(EverydayPrizeActivity.getStartingIntents(context));
    }

    public static void startCharacterSexSelection(Context context) {
        context.startActivity(CreateCharacterSexActivity.getStartingIntents(context));
    }

    public static void startCharacterClassSelection(Context context) {
        context.startActivity(CreateCharacterClassActivity.getStartingIntents(context));
    }

    public static void startCharacterAppearanceSelection(Context context) {
        context.startActivity(CreateCharacterAppearanceActivity.getStartingIntents(context));
    }

    public static void startCharacterNameSelection(Context context) {
        context.startActivity(CreateCharacterNameActivity.getStartingIntents(context));
    }

    public static void startCharacterSummary(Context context) {
        context.startActivity(CreateCharacterSummaryActivity.getStartingIntents(context));
    }

    public static void startMyProfile(Context context){
        context.startActivity(MyProfileActivity.getStartingIntents(context));
    }

    //TODO startRegistration, startLogin
}
