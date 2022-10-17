package com.example.backendbase.manager.entity.response;

import com.example.backendbase.manager.entity.*;
import com.example.backendbase.manager.entity.dto.GeneralServiceDTO;
import com.example.backendbase.manager.entity.dto.HandOverAssetsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomGroupResponse {

    @JsonProperty("group_id")
    private Long id;

    @JsonProperty("group_name")
    private String name;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("list_rooms")
    private List<Rooms> listRoom;

    @JsonProperty("list_general_service")
    private List<GeneralServiceDTO> listBasicServices;

    @JsonProperty("list_hand_over_assets")
    private List<HandOverAssetsDTO> listHandOverAssets;

}
