package com.example.backendbase.user.entity.request;

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

    private Set<String> roles;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String gender;

    @JsonProperty("address_city")
    private String city;

    @JsonProperty("address_district")
    private String district;

    @JsonProperty("address_wards")
    private String wards;

    @JsonProperty("address_more_detail")
    private String moreDetails;

    @JsonProperty("permission")
    private int[] permission;

}
