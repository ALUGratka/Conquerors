package pl.conquerors.app.util;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import pl.conquerors.app.R;

public class DateUtil {

    public static String getFormattedString(final SimpleDateFormat format, final Date date) {
        return format.format(date);
    }

    public static String getHourString(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    /**
     * dd.MM.yyyy
     */
    public static String getDateDottedString(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    /**
     * yyyy-MM-dd
     */
    public static String getDateHyphenString(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    public static String getDateHourString(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    public static String getDateLongString(final Context context, final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.date_long_format), Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    public static String getDayNameString(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    public static String getDayNameHourString(final Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("EEEE, HH:mm", Locale.getDefault());
        return getFormattedString(sdf, date);
    }

    public static String getRelativeDateString(final Context context, final Date date) {
        return getRelativeString(context, date, false);
    }

    public static String getRelativeDateHourString(final Context context, final Date date) {
        return getRelativeString(context, date, true);
    }

    private static String getRelativeString(final Context context, final Date date, final boolean withHour) {
        final Calendar toCheck = GregorianCalendar.getInstance();
        toCheck.setTime(date);

        final Calendar today = GregorianCalendar.getInstance();

        // this will help in case of year change
        // ex. when we have 31.12.2016 and want to format string of 01.01.2017 as 'tomorrow'
        final int yearDiff = toCheck.get(Calendar.YEAR) - today.get(Calendar.YEAR);
        // 365.25 taking leap-year into account
        final int compensation = (int) Math.floor(365.25f * yearDiff);

        final int dayDiff = toCheck.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) + compensation;

        final String relative;
        switch (dayDiff) {
            case 0:
                relative = context.getString(R.string.today) + (withHour ? ", " + getHourString(date) : "");
                break;
            case 1:
                relative = context.getString(R.string.tomorrow) + (withHour ? ", " + getHourString(date) : "");
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                relative = withHour ? getDayNameHourString(date) : getDayNameString(date);
                break;
            default:
                relative = withHour ? getDateHourString(date) : getDateDottedString(date);
                break;
        }

        return relative;
    }

    public static Date getDateFromString(final SimpleDateFormat format, final String date) {
        try {
            return format.parse(date);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return new Date(Long.MAX_VALUE);
    }

    public static boolean isDateValid(final Date date) {
        return date != null && date.before(new Date(Long.MAX_VALUE));
    }

    public static Date getDateFromLongFormatString(final String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return getDateFromString(sdf, date);
    }

    public static Date getDateFromDateString(final String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return getDateFromString(sdf, date);
    }

    public static Date getDateFromTimeString(final String time) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return getDateFromString(sdf, time);
    }

    public static boolean isSameDay(final Date dayOne, final Date dayTwo) {
        if (dayOne == null || dayTwo == null) {
            return false;
        }

        final Calendar one = GregorianCalendar.getInstance(Locale.getDefault());
        one.setTimeInMillis(dayOne.getTime());

        final Calendar two = GregorianCalendar.getInstance(Locale.getDefault());
        two.setTimeInMillis(dayTwo.getTime());

        return one.get(Calendar.YEAR) == two.get(Calendar.YEAR) && one.get(Calendar.DAY_OF_YEAR) == two.get(Calendar.DAY_OF_YEAR);
    }
}
