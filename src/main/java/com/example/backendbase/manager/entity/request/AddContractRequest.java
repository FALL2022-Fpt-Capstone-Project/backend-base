package com.example.backendbase.manager.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddContractRequest {

    //contract information
    @JsonProperty("contract_name")
    private String contractName;

    @JsonProperty("contract_price")
    private Double price;

    @JsonProperty("contract_deposit")
    private Double deposit;

    @JsonProperty("contract_bill_cycle")
    private Integer billCycle;

    @JsonProperty("contract_payment_cycle")
    private Integer paymentCycle;

    @JsonProperty("contract_start_date")
    private String startDate;

    @JsonProperty("contract_end_date")
    private String endDate;

    @JsonProperty("contract_note")
    private String note;

    @JsonProperty("contract_term")
    private Integer contractTerm;

    @JsonProperty("contract_img")
    private List<String> image;
    //--------------------------

    // renter information
    @JsonProperty("renter_old_id")
    private Long oldRenterId;

    @JsonProperty("renter_name")
    private String renterName;

    @JsonProperty("renter_phone_number")
    private String phoneNumber;

    @JsonProperty("renter_email")
    private String email;

    @JsonProperty("renter_gender")
    private Boolean gender;

    @JsonProperty("renter_identity_card")
    private String identityCard;
    //--------------------------

    // room information
    @JsonProperty("room_id")
    private Long roomId;

    @JsonProperty("room_floor")
    private Integer floor;

    @JsonProperty("group_id")
    private Long groupId;
    //--------------------------

    @JsonProperty("list_hand_over_assets")
    private List<HandOverAssetsRequest> basicAssets;

    @JsonProperty("list_general_service")
    private List<HandOverGeneralService> handOverGeneralServices;

    @JsonProperty("list_renter")
    private List<Renter> member;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class HandOverAssetsRequest {

        @JsonProperty("asset_id")
        private Long assetsId;

        @JsonProperty("asset_additional_name")
        private String assetsAdditionalName;

        @JsonProperty("asset_additional_type")
        private Long assetsAdditionalType;

        @JsonProperty("hand_over_asset_quantity")
        private int numberOfAsset;

        @JsonProperty("hand_over_asset_status")
        private Boolean handOverAssetStatus;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class HandOverGeneralService {
        private Long generalServiceId;

        private Integer handOverServiceIndex;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Renter {
        private String name;

        private Boolean gender;

        private String phoneNumber;

        private String identityCard;

        private String licensePlates;

        private String address;
    }
}
