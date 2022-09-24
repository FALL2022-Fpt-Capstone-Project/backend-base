package com.example.backendbase.user.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginRequest {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("password")
    private String password;
}
