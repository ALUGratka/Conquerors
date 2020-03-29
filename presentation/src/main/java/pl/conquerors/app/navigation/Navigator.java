package pl.conquerors.app.navigation;

import android.content.Context;

import pl.conquerors.app.view.everydayPrize.EverydayPrizeActivity;
import pl.conquerors.app.view.home.HomeActivity;
import pl.conquerors.app.view.login.LoginActivity;
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

    public static void startEverydayPrize(Context context) {
        context.startActivity(EverydayPrizeActivity.getStartingIntents(context));
    }

    //TODO startRegistration, startLogin
}
