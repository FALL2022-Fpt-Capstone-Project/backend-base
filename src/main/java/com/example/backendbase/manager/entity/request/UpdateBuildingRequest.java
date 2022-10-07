package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateBuildingRequest {

    @JsonProperty("building_id")
    private Long buildingId;

    @JsonProperty("building_name")
    private String buildingName;

    @JsonProperty("building_total_room")
    private int totalRoom;

    @JsonProperty("building_total_floor")
    private int totalFloor;

    @JsonProperty("building_address_city")
    private String city;

    @JsonProperty("building_address_district")
    private String district;

    @JsonProperty("building_address_wards")
    private String wards;

    @JsonProperty("building_address_more_detail")
    private String moreAddressDetail;

}
