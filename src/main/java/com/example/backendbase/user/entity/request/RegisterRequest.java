package com.example.backendbase.user.entity.request;

import com.example.backendbase.security.enums.ERole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    @JsonProperty("user_name")
    private String userName;

    private String password;

    @JsonProperty("user_infor_id")
    private int userInforId;

    @JsonProperty("identity_id")
    private int identityId;

    private Set<String> roles;

}
