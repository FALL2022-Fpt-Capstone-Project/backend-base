package com.example.backendbase.common.utils;

import com.example.backendbase.common.entity.response.Response;
import com.example.backendbase.common.entity.response.ResponseStatus;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtils {


    public static ResponseEntity<Object> httpResponse(ResponseStatus status, Object responseBody) {
        return ResponseEntity.ok(new Response(status, responseBody));
    }

    public static ResponseEntity<Object> httpResponse(Object responseBody) {
        return ResponseEntity.ok(new Response(
                ResponseStatus.builder()
                        .code(HttpStatus.OK.value())
                        .message(HttpStatus.OK.getReasonPhrase())
                        .build(),
                responseBody));
    }
}
