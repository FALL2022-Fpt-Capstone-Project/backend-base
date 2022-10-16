package com.example.backendbase.common.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@UtilityClass
public class TimeUtils {

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String showTimestamp(Timestamp timestamp) {
        return TIME_FORMAT.format(timestamp);
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }

    @SneakyThrows
    public static Timestamp parseToTimestamp(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse(time);
        return new java.sql.Timestamp(parsedDate.getTime());
    }

    public static Timestamp parseToTimestamp(LocalDateTime time) {
        return Timestamp.valueOf(time);
    }
}
