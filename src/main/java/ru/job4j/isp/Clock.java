package ru.job4j.isp;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public interface Clock {

    LocalDateTime getLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute);

    Calendar getCalendarTime(int year, int month, int dayOfMonth, int hour, int minute);

    Date getDateTime(int year, int month, int dayOfMonth, int hour, int minute);

    TimeZone getTimeZoneTime(int year, int month, int dayOfMonth, int hour, int minute);
}
