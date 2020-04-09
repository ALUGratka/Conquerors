package pl.conquerors.app.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DialogUtil {

    public static void showDatePicker(final Context context, final Date dateToShow, final OnDateSetListener listener) {
        final Calendar cal = GregorianCalendar.getInstance();
        if (dateToShow != null) {
            cal.setTime(dateToShow);
        }

        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(final DatePicker datePicker, final int year, final int month, final int dayOfMonth) {
                final Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(0);
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                listener.onDateSet(calendar.getTime());
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public static void showTimePicker(final Context context, final Date dateToShow, final boolean show24hour, final OnDateSetListener listener) {
        final Calendar cal = GregorianCalendar.getInstance();
        if (dateToShow != null) {
            cal.setTime(dateToShow);
        }

        final TimePickerDialog datePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
                final Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTimeInMillis(0);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                listener.onDateSet(calendar.getTime());
            }

        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), show24hour);
        datePickerDialog.show();
    }

    public static AlertDialog showSimpleDialog(final Context context, final String title, final String message) {
        return showSimpleDialog(context, title, message, null);
    }

    public static AlertDialog showSimpleDialog(final Context context, final String title, final String message, DialogInterface.OnClickListener positive) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(title).setMessage(message).setPositiveButton(android.R.string.yes, positive).show();
    }

    public static AlertDialog showSimpleDialog(final Context context, final String title, final String message, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        return showSimpleDialog(context, title, message, android.R.string.yes, positive, android.R.string.no, negative);
    }

    public static AlertDialog showSimpleDialog(
            final Context context,
            final String title,
            final String message,
            @StringRes final int positiveId,
            final DialogInterface.OnClickListener positive,
            @StringRes final int negativeId,
            final DialogInterface.OnClickListener negative) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveId, positive)
                .setNegativeButton(negativeId, negative)
                .show();
    }


    public interface OnDateSetListener {
        void onDateSet(final Date date);
    }
}
