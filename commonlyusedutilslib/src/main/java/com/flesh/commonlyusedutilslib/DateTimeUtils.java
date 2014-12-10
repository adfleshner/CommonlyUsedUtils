package com.flesh.commonlyusedutilslib;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateTimeUtils {
    
    private String TimeFromTodayAccuracyToTheMinute(String date){
        String Time = "";
        DateTime StoryTime = new DateTime(date);
        DateTime NowTime = new DateTime();
        int minutes = Minutes.minutesBetween(StoryTime, NowTime).getMinutes();
        int hours = Hours.hoursBetween(StoryTime, NowTime).getHours();
        int days = Days.daysBetween(StoryTime, NowTime).getDays();
        if(days > 0){
            Time  = Time + " " + days + " d";
        }
        if(BelowADay(hours) > 0){
            int timeHours = BelowADay(hours);
            Time  = Time + " " + timeHours + " h";
        }
        if(BelowAHour(minutes) > 0){
            int timeMins = BelowAHour(minutes);
            Time  = Time + " " + timeMins + " m";
        }
        
        Time = Time + " ago.";
        if(Time.length()==5){
            Time = "less then a minute ago.";
        }
        return Time;
    }
    
    private String TimeFromTodayAccuracyToTheHour(String date){
        String Time = "";
        DateTime StoryTime = new DateTime(date);
        DateTime NowTime = new DateTime();
        int hours = Hours.hoursBetween(StoryTime, NowTime).getHours();
        int days = Days.daysBetween(StoryTime, NowTime).getDays();
        if(days > 0){
            Time  = Time + " " + days + " d";
        }
        if(BelowADay(hours) > 0){
            int timeHours = BelowADay(hours);
            Time  = Time + " " + timeHours + " h";
        }
        Time = Time + " ago.";
        if(Time.length()==5){
            Time = "less then a hour ago.";
        }
        return Time;
    }
    
    private String TimeFromTodayAccuracyToTheDay(String date){
        String Time = "";
        DateTime StoryTime = new DateTime(date);
        DateTime NowTime = new DateTime();
        int hours = Hours.hoursBetween(StoryTime, NowTime).getHours();
        int days = Days.daysBetween(StoryTime, NowTime).getDays();
        if(days > 0){
            Time  = Time + " " + days + " d";
        }
        Time = Time + " ago.";
        if(Time.length()==5){
            Time = "less then a day ago.";
        }
        return Time;
    }

    private int BelowAHour(int minutes) {
        int temp = minutes;
        while(temp>=1440){// minus by the day
            temp = temp-1440;
        }
        while(temp>=60){//minus by the hour
            temp = temp-60;
        }
        return temp;
    }
    private int BelowADay(int hours) {
        int temp = hours;
        while(temp>=24){//minus by the day
            temp = temp-24;
        }
        return temp;
    }

    /**
     * gives time in between the two dates give ( Joda Time lib is needed )
     *
     * @param start
     * @param end
     * @return
     */
    public static String GetTimeInBetween(Date start, Date end) {
        DateTime DateTimeStart = new DateTime(start);
        DateTime DateTimeEnd = new DateTime(end);
        int seconds = Seconds.secondsBetween(DateTimeStart, DateTimeEnd).getSeconds();
        int minutes = Minutes.minutesBetween(DateTimeStart, DateTimeEnd).getMinutes();
        int hours = Hours.hoursBetween(DateTimeStart, DateTimeEnd).getHours();
        int days = Days.daysBetween(DateTimeStart, DateTimeEnd).getDays();
        if (seconds < 60 && seconds > 0) {
            return seconds + " s";
        } else if (minutes < 60 && minutes > 0) {
            return minutes + " m";
        } else if (hours < 24 && hours > 0) {
            return hours + " h";
        } else if (days < 365 && days > 0) {
            return days + " d";
        } else {
            return "over a year ago";
        }
    }

    /**
     * gives time in between the two dates give ( Joda Time lib is needed )
     *
     * @param start now
     * @param end item
     * @return
     */
    public static String GetTimeInBetween(DateTime start, DateTime end) {
        int seconds = Seconds.secondsBetween(start, end).getSeconds();
        int minutes = Minutes.minutesBetween(start, end).getMinutes();
        int hours = Hours.hoursBetween(start, end).getHours();
        int days = Days.daysBetween(start, end).getDays();
        if (seconds < 60 && seconds > 0) {
            return seconds + " s";
        } else if (minutes < 60 && minutes > 0) {
            return minutes + " m";
        } else if (hours < 24 && hours > 0) {
            return hours + " h";
        } else if (days < 365 && days > 0) {
            return days + " d";
        } else {
            return "over a year ago";
        }
    }

    public static DateTime StringToDateTime(String date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormat);
        DateTime dt = formatter.parseDateTime(date);
        return dt;
    }
}
