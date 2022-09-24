package com.example.backendbase.common.exceptions;

import com.example.backendbase.common.entity.response.ResponseStatus;
import com.example.backendbase.common.entity.response.Response;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleUnwantedException(Exception e) {
        var responseStatus = ResponseStatus.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
        e.printStackTrace();
        return new Response(responseStatus, null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.FORBIDDEN)
    public Response handleNotFoundUsername(UsernameNotFoundException e) {
        var responseStatus = ResponseStatus.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .build();
        e.printStackTrace();
        return new Response(responseStatus, null);
    }


}
