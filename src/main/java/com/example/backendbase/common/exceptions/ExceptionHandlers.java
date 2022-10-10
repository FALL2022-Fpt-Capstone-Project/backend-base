package com.example.backendbase.common.exceptions;

import com.example.backendbase.common.entity.response.Response;
import com.example.backendbase.common.entity.response.ResponseStatus;
import com.example.backendbase.manager.exception.ManagerException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(AccessDeniedException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.FORBIDDEN)
    public Response handleAuthException(AccessDeniedException e) {
        var responseStatus = ResponseStatus.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage())
                .build();
        e.printStackTrace();
        return new Response(responseStatus, null);
    }

    @ExceptionHandler(AuthenticationException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleAuthenticationException(AuthenticationException e) {

        if (e instanceof BadCredentialsException) {
            e.printStackTrace();
            return new Response(ResponseStatus.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message("Wrong username or password!!")
                    .build(), null);
        }

        if (e instanceof UsernameNotFoundException) {
            e.printStackTrace();
            return new Response(ResponseStatus.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message("Wrong username!!")
                    .build(), null);
        }
        e.printStackTrace();
        return new Response(ResponseStatus.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build(), null);
    }

    @ExceptionHandler(ManagerException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleBusinessException(ManagerException e) {
        var responseStatus = ResponseStatus.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
        e.printStackTrace();
        return new Response(responseStatus, null);
    }


}
