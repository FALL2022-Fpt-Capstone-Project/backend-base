package com.example.backendbase.manager.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffAccountResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("created_date")
    private Timestamp createdDate;

    @JsonProperty("role")
    private Set<String> role;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

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

    @JsonProperty("deactivate")
    private boolean isDeactivate;
}
