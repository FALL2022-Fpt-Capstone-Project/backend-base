package com.example.backendbase.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

@UtilityClass
public class ParseUtils {

    public static final String SPLIT_REGEX = ",";

    public int[] parseStringArrayToIntArray(String data) {
        if (Strings.isBlank(data)) return null;

        String[] numberStrs = data.split(SPLIT_REGEX);
        int[] numbers = new int[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        return numbers;
    }

    public String parseIntArrayToString(int[] data) {
        if (data.length == 0) return "";
        String[] strings = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            strings[i] = String.valueOf(data[i]);
        }
        return String.join(SPLIT_REGEX, strings);
    }
}
