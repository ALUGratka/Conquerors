package pl.conquerors.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import pl.conquerors.app.domain.model.Character;

import static android.preference.PreferenceManager.*;
import static pl.conquerors.app.util.PreferencesUtility.APPEARANCE_BLOUSE_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.APPEARANCE_EYE_COLOR_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.APPEARANCE_HAIR_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.APPEARANCE_HAT_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.APPEARANCE_PANTS_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.APPEARANCE_SHOES_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.CHARACTER_CLASS_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.LOGGED_IN_PREF;
import static pl.conquerors.app.util.PreferencesUtility.NAME_SELECTED;
import static pl.conquerors.app.util.PreferencesUtility.SEX_SELECTED;

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

    public static void setCharacterClass(Context context, int selectedClass) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(CHARACTER_CLASS_SELECTED, selectedClass);
        editor.apply();
    }

    public static int getCharacterClass(Context context) {
        return getPreferences(context).getInt(CHARACTER_CLASS_SELECTED, 0);
    }

    public static void setCharacterName(Context context, String selectedName) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(NAME_SELECTED, selectedName);
        editor.apply();
    }

    public static String getCharacterName(Context context) {
        return getPreferences(context).getString(NAME_SELECTED, "ala");
    }

    public static void setCharacterSex(Context context, int selectedSex) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(SEX_SELECTED, selectedSex);
        editor.apply();
    }

    public static int getCharacterSex(Context context) {
        return getPreferences(context).getInt(SEX_SELECTED, 0);
    }

    public static void setCharacterHair(Context context, int selectedHair) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(APPEARANCE_HAIR_SELECTED, selectedHair);
        editor.apply();
    }

    public static int getCharacterHair(Context context) {
        return getPreferences(context).getInt(APPEARANCE_HAIR_SELECTED, 0);
    }

    public static void setCharacterHat(Context context, int selectedHat) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(APPEARANCE_HAT_SELECTED, selectedHat);
        editor.apply();
    }

    public static int getCharacterHat(Context context) {
        return getPreferences(context).getInt(APPEARANCE_HAT_SELECTED, 0);
    }

    public static void setCharacterEyeColor(Context context, int selectedEyeColor) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(APPEARANCE_EYE_COLOR_SELECTED, selectedEyeColor);
        editor.apply();
    }

    public static int getCharacterEyeColor(Context context) {
        return getPreferences(context).getInt(APPEARANCE_EYE_COLOR_SELECTED, 0);
    }

    public static void setCharacterBlouse(Context context, int selectedBlouse) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(APPEARANCE_BLOUSE_SELECTED, selectedBlouse);
        editor.apply();
    }

    public static int getCharacterBlouse(Context context) {
        return getPreferences(context).getInt(APPEARANCE_BLOUSE_SELECTED, 0);
    }

    public static void setCharacterPants(Context context, int selectedPants) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(APPEARANCE_PANTS_SELECTED, selectedPants);
        editor.apply();
    }

    public static int getCharacterPants(Context context) {
        return getPreferences(context).getInt(APPEARANCE_PANTS_SELECTED, 0);
    }

    public static void setCharacterShoes(Context context, int selectedShoes) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(APPEARANCE_SHOES_SELECTED, selectedShoes);
        editor.apply();
    }

    public static int getCharacterShoes(Context context) {
        return getPreferences(context).getInt(APPEARANCE_SHOES_SELECTED, 0);
    }
}
