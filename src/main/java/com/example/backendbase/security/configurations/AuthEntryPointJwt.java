package com.example.backendbase.security.configurations;

import com.example.backendbase.common.entity.response.Response;
import com.example.backendbase.common.entity.response.ResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        var responseStatus = ResponseStatus.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(authException.getMessage())
                .build();

        final Map<String, Object> body = new HashMap<>();
        body.put("path", request.getServletPath());


        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), new Response(responseStatus, body));
    }
}