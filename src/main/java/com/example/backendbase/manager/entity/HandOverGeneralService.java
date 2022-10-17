package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
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
@Table(name = "Manager.Hand_Over_General_Services")
@DynamicInsert
@DynamicUpdate
public class HandOverGeneralService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hand_over_general_service_id")
    @JsonProperty("hand_over_general_service_id")
    private Long id;

    @Column(name = "general_service_id")
    @JsonProperty("general_service_id")
    private Long generalServiceId;

    @Column(name = "hand_over_general_service_index")
    @JsonProperty("hand_over_general_service_index")
    private Integer handOverIndex;

    @Column(name = "contract_id")
    @JsonProperty("contract_id")
    private Long contractId;

    @Column(name = "hand_over_general_service_date_delivery")
    @JsonProperty("hand_over_general_service_date_delivery")
    private Timestamp dateOfDelivery;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

}
