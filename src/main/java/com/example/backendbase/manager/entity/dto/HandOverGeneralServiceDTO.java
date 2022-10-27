package com.example.backendbase.manager.entity.dto;

import com.example.backendbase.manager.entity.GeneralService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HandOverGeneralServiceDTO extends GeneralService implements Serializable {
    @JsonProperty("service_name")
    private String name;

    private String serviceTypeName;

    private String serviceShowName;

    private String note;

    @JsonProperty("hand_over_general_service_index")
    private Integer index;

    @JsonProperty("hand_over_general_service_date_delivery")
    private Timestamp dateDelivery;

}
