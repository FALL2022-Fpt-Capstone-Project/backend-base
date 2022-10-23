package com.example.backendbase.manager.entity.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class NumberOfContractResponse {
    private Integer duration;

    private Integer expiredContract;

    private Integer almostExpiredContract;

    private Integer latestContract;
}
