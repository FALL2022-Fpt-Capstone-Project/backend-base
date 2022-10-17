package com.example.backendbase.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ParseUtils {

    public static final String SPLIT_REGEX = ",";

    public static int[] parseStringArrayToIntArray(String data) {
        if (Strings.isBlank(data)) return null;

        String[] numberStrs = data.split(SPLIT_REGEX);
        int[] numbers = new int[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        return numbers;
    }

    public static String parseIntArrayToString(int[] data) {
        if (data.length == 0) return "";
        String[] strings = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            strings[i] = String.valueOf(data[i]);
        }
        return String.join(SPLIT_REGEX, strings);
    }

    public static List<String> getAllNameEnumClass(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    public static List<?> getAllEnum(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).collect(Collectors.toList());
    }
}
