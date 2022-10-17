package com.example.backendbase.manager.entity.dto;

import com.example.backendbase.manager.entity.HandOverAssets;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HandOverAssetsDTO extends HandOverAssets implements Serializable {
    @JsonProperty("hand_over_asset_id")
    private Long id;

    @JsonProperty("asset_name")
    private String name;

    @JsonProperty("asset_type_show_name")
    private String typeShowName;

    @JsonProperty("asset_type")
    private String typeName;
}
