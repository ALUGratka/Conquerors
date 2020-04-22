package pl.conquerors.app.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import pl.conquerors.app.domain.model.User;

import static android.preference.PreferenceManager.*;
import static pl.conquerors.app.util.PreferencesUtility.LOGGED_IN_PREF;
import static pl.conquerors.app.util.PreferencesUtility.USER_NAME_PREF;
import static pl.conquerors.app.util.PreferencesUtility.USER_PREF;

public class SharedPreferenceUtil {

    static SharedPreferences getPreferences(Context context) {
        return getDefaultSharedPreferences(context);
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static void setUserName(Context context, String userName) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(USER_NAME_PREF, userName);
        editor.apply();
    }

    public static String getUserName(Context context) {
        return getPreferences(context).getString(USER_NAME_PREF, null);
    }

    public static void setUser(Context context, User user) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(USER_PREF, json);
        editor.apply();
    }

    public static User getUser(Context context) {
        Gson gson = new Gson();
        String json = getPreferences(context).getString(USER_PREF, null);
        return gson.fromJson(json, User.class);
    }
}
