package com.example.backendbase.common.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtils {

    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String showTimestamp(Timestamp timestamp){
        return TIME_FORMAT.format(timestamp);
    }

    public static Timestamp getCurrentTime(){
        return new Timestamp(new Date().getTime());
    }
}
