package pl.conquerors.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.preference.PreferenceManager.*;
import static pl.conquerors.app.util.PreferencesUtility.LOGGED_IN_PREF;
import static pl.conquerors.app.util.PreferencesUtility.USER_NAME_PREF;

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
}
