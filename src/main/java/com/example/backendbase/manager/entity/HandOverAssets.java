package com.example.backendbase.manager.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "Manager.HandOverAssets")
public class HandOverAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hand_over_asset_id")
    private Long id;

    @Column(name = "hand_over_asset_quantity")
    private int quantity;

    @Column(name = "hand_over_asset_unit")
    private String unit;

    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "contract_id")
    private Long contractId;
}
