package com.flightDomain.flightbook.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeManager {

    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public static Duration parseTime(String time) {
        if (time == null || time.isEmpty()) {
            return Duration.ZERO;
        }
        String[] parts = time.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid time format, expected HH:mm");
        }
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return Duration.ofHours(hours).plusMinutes(minutes);
    }

    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    public static Duration calculateDuration(LocalTime start, LocalTime end) {
        return Duration.between(start, end);
    }

    public static Duration parseLocalTime(LocalTime time) {
        if (time == null) {
            return Duration.ZERO;
        }
        return Duration.ofHours(time.getHour()).plusMinutes(time.getMinute());
    }

    public static String formatLocalTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
