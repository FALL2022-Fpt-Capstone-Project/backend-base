package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "Manager.General_Services")
@DynamicUpdate
@DynamicInsert
public class GeneralService {

    public GeneralService(Long serviceId, Long contractId, Long serviceType, Double price) {
        this.serviceId = serviceId;
        this.conntractId = contractId;
        this.serviceType = serviceType;
        this.servicePrice = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_service_id")
    @JsonProperty("general_service_id")
    private Long id;

    @Column(name = "service_id")
    @JsonProperty("service_id")
    private Long serviceId;

    @Column(name = "contract_id")
    @JsonProperty("contract_id")
    private Long conntractId;

    @Column(name = "service_type_id")
    @JsonProperty("service_type_id")
    private Long serviceType;

    @Column(name = "service_price")
    @JsonProperty("service_price")
    private Double servicePrice;

    @Column(name = "note")
    @JsonProperty("note")
    private String note;

    @Column(name = "created_by")
    @JsonIgnore
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    @JsonIgnore
    private Timestamp updatedTime = TimeUtils.getCurrentTime();


}
