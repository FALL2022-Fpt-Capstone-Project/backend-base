package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateBuildingRequest {

    @JsonProperty("building_id")
    private Long buildingId;

    @JsonProperty("building_name")
    private String buildingName;

    @JsonProperty("building_total_room")
    private int totalRoom;

    @JsonProperty("address_id")
    private  Long addressId;


}
