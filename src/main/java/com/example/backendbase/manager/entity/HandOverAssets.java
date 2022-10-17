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
@Table(name = "Manager.Hand_Over_Assets")
@DynamicInsert @DynamicUpdate
public class HandOverAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hand_over_asset_id")
    @JsonProperty("hand_over_asset_id")
    private Long id;

    @Column(name = "hand_over_asset_quantity")
    @JsonProperty("hand_over_asset_quantity")
    private int quantity;

    @Column(name = "asset_id")
    @JsonProperty("asset_id")
    private Long assetId;

    @Column(name = "contract_id")
    @JsonProperty("contract_id")
    private Long contractId;

    @Column(name = "hand_over_asset_status")
    @JsonProperty("hand_over_asset_status")
    private Boolean status;

    @Column(name = "hand_over_asset_date_delivery")
    @JsonProperty("hand_over_asset_date_delivery")
    private Timestamp dateOfDelivery;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

}
