package ru.job4j.isp;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class JavaClock implements Clock {

    @Override
    public LocalDateTime getLocalDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    @Override
    public Calendar getCalendarTime(int year, int month, int dayOfMonth, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, hour, minute);
        return calendar;
    }

    @Override
    public Date getDateTime(int year, int month, int dayOfMonth, int hour, int minute) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TimeZone getTimeZoneTime(int year, int month, int dayOfMonth, int hour, int minute) {
        throw new UnsupportedOperationException();
    }
}
