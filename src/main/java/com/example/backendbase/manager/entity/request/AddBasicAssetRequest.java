package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AddBasicAssetRequest {
    @JsonProperty("asset_name")
    String name;

    @JsonProperty("asset_unit")
    String unit;

    @JsonProperty("asset_type")
    String type;
}
