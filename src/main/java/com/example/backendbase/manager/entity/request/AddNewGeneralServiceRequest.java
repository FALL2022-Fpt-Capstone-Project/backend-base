package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddNewGeneralServiceRequest {
    private Long contractId;

    private Long serviceId;

    private Double generalServicePrice;

    private Long generalServiceType;

}
