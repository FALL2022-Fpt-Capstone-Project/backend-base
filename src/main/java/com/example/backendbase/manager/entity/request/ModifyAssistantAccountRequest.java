package com.example.backendbase.manager.entity.request;

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
public class ModifyAssistantAccountRequest {
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

    @JsonProperty("full_name")
    private String fullName;

    private String gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address_more_detail")
    private String moreDetails;
}
