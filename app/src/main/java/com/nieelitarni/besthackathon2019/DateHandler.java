package com.nieelitarni.besthackathon2019;

import android.icu.util.Calendar;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

import java.util.Date;

public final class DateHandler
{
    public static int getDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getHour(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    public static long getMillisecond(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MILLISECOND);
    }

    public static String getDayOfWeekString(Date date)
    {
        return (String) DateFormat.format("EEEE", date);
    }

    public static String getMonthString(Date date)
    {
        return (String) DateFormat.format("MMM",  date);
    }

    public static String getTimeAgo(Date date)
    {
        return (String) DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);
    }
}