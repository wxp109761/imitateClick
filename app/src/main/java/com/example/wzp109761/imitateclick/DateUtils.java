package com.example.wzp109761.imitateclick;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间解析工具
 */

public class DateUtils {

    public static String parseTime(long publishTime) {
        long time;
        time = (System.currentTimeMillis() - publishTime) / 1000;
        int day, hour, minute, senond;
        day = (int) (time / 3600 / 24);

        if (day == 1)
            return "1天前";
        if (day > 1)
            return new SimpleDateFormat("yyyy-MM-dd").format(publishTime);
        hour = (int) (time / 3600);
        if (hour > 0)
            return String.valueOf(hour) + "小时前";
        minute = (int) (time / 60);
        if (minute > 0)
            return String.valueOf(minute) + "分钟前";
        senond = (int) time;
        if (senond >= 0)
            return "刚刚";
        return "";
    }

    public static String parseDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    /**
     * helper method to get string in format hh:mm:ss from miliseconds
     *
     * @param millis (application time in foreground)
     * @return string in format hh:mm:ss from miliseconds
     */
    public static String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return (hours + " h " + minutes + " m " + seconds + " s");
    }
}
