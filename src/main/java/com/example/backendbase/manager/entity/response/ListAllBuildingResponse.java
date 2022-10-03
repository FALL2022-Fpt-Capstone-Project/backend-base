package com.example.backendbase.manager.entity.response;

import com.example.backendbase.manager.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
@Builder
public class ListAllBuildingResponse {

    @JsonProperty("building_id")
    private Long id;

    @JsonProperty("building_name")
    private String buildingName;

    @JsonProperty("building_total_rooms")
    private Integer totalRooms;

    @JsonProperty("building_total_floor")
    private Integer totalFloors;

    @JsonProperty("updated_time")
    private Timestamp updatedTime;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("address_city")
    private String city;

    @JsonProperty("address_district")
    private String district;

    @JsonProperty("address_wards")
    private String wards;

    @JsonProperty("address_more_detail")
    private String moreDetails;
}
