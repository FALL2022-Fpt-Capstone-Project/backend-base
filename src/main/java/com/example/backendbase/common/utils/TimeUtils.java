package com.example.backendbase.common.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.time.*;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@UtilityClass
public class TimeUtils {

    private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String showTimestamp(Timestamp timestamp) {
        return TIME_FORMAT.format(timestamp);
    }

    public static Timestamp getCurrentTime() {
        Instant i = Instant.now();
        return Timestamp.from(i);
    }
    @SneakyThrows
    public static Timestamp parseToTimestamp(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(time);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(time);
                return new java.sql.Timestamp(parsedDate.getTime());
            } catch (Exception ex) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date parsedDate = dateFormat.parse(time);
                    return new java.sql.Timestamp(parsedDate.getTime());
                } catch (Exception exc) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date parsedDate = dateFormat.parse(time);
                    return new java.sql.Timestamp(parsedDate.getTime());
                }
            }
        }
    }

    public static Timestamp parseToTimestamp(LocalDateTime time) {
        return Timestamp.valueOf(time);
    }
}
