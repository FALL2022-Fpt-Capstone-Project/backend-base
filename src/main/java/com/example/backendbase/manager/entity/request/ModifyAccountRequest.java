package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyAccountRequest {
    @JsonProperty("user_name")
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

    @JsonProperty("permission")
    private int[] permission;

    @JsonProperty("deactivate")
    private Boolean deactivate;
}
