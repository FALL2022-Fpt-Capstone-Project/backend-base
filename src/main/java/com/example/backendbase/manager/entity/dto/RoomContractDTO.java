package com.example.backendbase.manager.entity.dto;

import com.example.backendbase.manager.entity.Contracts;
import com.example.backendbase.manager.entity.Renters;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomContractDTO extends Contracts {
    private String renterName;

    private List<HandOverGeneralServiceDTO> listHandOverServices;

    private List<HandOverAssetsDTO> listHandOverAssets;

    private List<Renters> listRenter;
}
