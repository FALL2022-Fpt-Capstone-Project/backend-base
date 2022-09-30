package com.example.backendbase.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "Manager.Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @JsonProperty("address_id")
    private Long id;

    @Column(name = "address_city")
    @JsonProperty("address_city")
    private String city;

    @Column(name = "address_district")
    @JsonProperty("address_district")
    private String district;

    @Column(name = "address_wards")
    @JsonProperty("address_wards")
    private String wards;

    @Column(name = "address_more_detail")
    @JsonProperty("address_more_detail")
    private String moreDetails;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Buildings buildings;

    @Column(name = "updated_time")
    @JsonProperty("updated_time")
    private Timestamp updatedTime;

    @Column(name = "created_by")
    @JsonProperty("created_by")
    private String createdBy;
}

