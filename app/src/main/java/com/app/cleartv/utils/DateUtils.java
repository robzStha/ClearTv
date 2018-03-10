package com.app.cleartv.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    static final String FORMAT_FROM_DATE = "MM/yyyy";//02/2007

    /**
     * @param calendar instance to the selected day
     * @return date string in required format {@link DateUtils#FORMAT_FROM_DATE}
     */
    public static String getStartDate(Calendar calendar) {
        if (calendar != null) {
            DateFormat dateFormat = new SimpleDateFormat(FORMAT_FROM_DATE, Locale.getDefault());
            return dateFormat.format(calendar.getTime());
        } else {
            return null;
        }
    }

    public static String getFinishDate(Calendar calendar) {
        return getStartDate(calendar);
    }

    /**
     * @param start  start date
     * @param finish end date
     * @return true if end date is after the start date else return false
     */
    public static boolean isFinishDateValid(String start, String finish) {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_FROM_DATE, Locale.getDefault());
        boolean isValid;
        try {
            Date startDate = dateFormat.parse(start);
            Date finishDate = dateFormat.parse(finish);
            isValid = finishDate.compareTo(startDate) >= 0;
        } catch (ParseException e) {
            e.printStackTrace();
            isValid = false;
        }
        return isValid;
    }

    public static Calendar getCalendarFromString(String stringDate) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_FROM_DATE, Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;

    }
}
