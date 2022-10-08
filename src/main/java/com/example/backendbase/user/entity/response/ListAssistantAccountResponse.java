package com.example.backendbase.user.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListAssistantAccountResponse {
    private Long id;

    @JsonProperty("user_name")
    private String username;

    @JsonIgnore
    private int identityId;

    @JsonProperty("created_date")
    private Timestamp createdDate;

    private Set<String> role;

    @Column(name = "full_name")
    @JsonProperty("full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("address_city")
    private String city;

    @JsonProperty("address_district")
    private String district;

    @JsonProperty("address_wards")
    private String wards;

    @Column(name = "address_more_detail")
    @JsonProperty("address_more_detail")
    private String moreDetails;
}
