package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePassRequest {

    @JsonProperty("new_password")
    private String newPassword;

}
