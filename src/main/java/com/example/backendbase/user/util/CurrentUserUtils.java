package com.example.backendbase.user.util;

import com.example.backendbase.security.service.UserAuthenDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class CurrentUserUtils {

    private CurrentUserUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getCurrentUser() {
        if (Objects.isNull((SecurityContextHolder.getContext().getAuthentication())))
            return "Undefied";
        return ((UserAuthenDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

}
