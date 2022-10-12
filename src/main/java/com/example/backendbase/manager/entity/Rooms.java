package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
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
    private Long id;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_floor")
    private Integer floor;

    @Column(name = "room_limit_people")
    private Integer limitPeople;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    @Column(name = "group_id")
    private Long roomGroups;

    @Column(name = "contract_id")
    private Long contracts;
}
