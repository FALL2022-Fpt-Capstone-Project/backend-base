package com.example.backendbase.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeAssistantPassResponse {

    private String account;

    @JsonProperty("new_password")
    private String newPassWord;
}
