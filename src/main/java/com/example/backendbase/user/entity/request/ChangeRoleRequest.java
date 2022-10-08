package com.example.backendbase.user.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRoleRequest {

    @JsonProperty("account_id")
    private Long id;

    @JsonSetter("account_id")
    public void setId(Long s) {
        if (s != null) {
            id = s;
        }
    }

    @JsonProperty("username")
    private String userName;

    @JsonProperty("role")
    private Set<String> role;
}
