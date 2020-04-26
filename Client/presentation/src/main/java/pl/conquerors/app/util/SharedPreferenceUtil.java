package pl.conquerors.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.preference.PreferenceManager.*;
import static pl.conquerors.app.util.PreferencesUtility.LOGGED_IN_PREF;

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

    public static void setCharacterClass(Context context, int charclass) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt("class", charclass);
        editor.apply();
    }

    public static int getCharacterClass(Context context) {
        return getPreferences(context).getInt("class", 0);
    }

    public static void setCharacterName(Context context, String charName) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("name", charName);
        editor.apply();
    }

    public static String getCharacterName(Context context) {
        return getPreferences(context).getString("name", "ala");
    }
}
