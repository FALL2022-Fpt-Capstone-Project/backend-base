package com.example.backendbase.manager.entity.dto;

import com.example.backendbase.manager.entity.GeneralService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GeneralServiceDTO extends GeneralService implements Serializable {
    @JsonProperty("service_name")
    private String name;

    private String serviceTypeName;

    private String serviceShowName;

}
