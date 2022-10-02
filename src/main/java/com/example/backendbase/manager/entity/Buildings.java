package com.example.backendbase.manager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Manager.Buildings")
public class Buildings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    @JsonProperty("building_id")
    private Long id;

    @Column(name = "building_name")
    @JsonProperty("building_name")
    private String buildingName;

    @Column(name = "building_total_rooms")
    @JsonProperty("building_total_rooms")
    private int totalRooms;

    @Column(name = "building_total_floor")
    private int totalFloors;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("address_id")
    private Address address;

    @Column(name = "updated_time")
    @JsonProperty("updated_time")
    private Timestamp updatedTime;

    @Column(name = "created_by")
    @JsonProperty("created_by")
    private String createdBy;
}
