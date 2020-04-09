package pl.conquerors.app.util;

import android.text.TextUtils;
import android.util.Patterns;

public class Validator {
    public Validator() { }

    public static boolean isEmailValid(final String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isLengthValid(final String input, final int min, final int max){
        if(input != null && input.length() >= min && input.length() <= max) return true;
        return false;
    }

    public static boolean isLengthValid( final String input, final int exactLength) {
        if( input != null && input.length() == exactLength) return true;
        return false;
    }

    public static boolean isInRange (final int input, final int min, final int max){
        if( input >= min && input <= max) return true;
        return false;
    }
}
