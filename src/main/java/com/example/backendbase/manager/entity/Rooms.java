package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
@Entity @Table(name = "Manager.Rooms")
@DynamicInsert
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    @JsonProperty("room_id")
    private Long id;

    @Column(name = "room_name")
    @JsonProperty("room_name")
    private String name;

    @Column(name = "room_floor")
    @JsonProperty("room_floor")
    private Integer floor;

    @Column(name = "room_limit_people")
    @JsonProperty("room_limit_people")
    private Integer limitPeople;

    @Column(name = "room_current_water_index")
    @JsonProperty("room_current_water_index")
    private Integer currentWaterIndex;

    @Column(name = "room_current_electric_index")
    @JsonProperty("room_current_electric_index")
    private Integer currentElectricIndex;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    @Column(name = "group_id")
    @JsonProperty("group_id")
    private Long roomGroups;

    @Column(name = "contract_id")
    @JsonProperty("contract_id")
    private Long contracts;
}
