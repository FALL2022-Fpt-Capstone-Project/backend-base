package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Manager.Room_Groups")
@DynamicUpdate
@DynamicInsert
// Bảng này lưu trữ thông tin của nhóm phòng cho khách thuê lại
public class RoomGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name")
    private String name;

    @Column(name = "created_by")
    @JsonIgnore
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    @JsonIgnore
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    @Column(name = "group_current_water_index")
    private Integer currentWaterIndex;

    @Column(name = "group_current_electric_index")
    private Integer currentElectricIndex;


    //One to one with Table Address
    //Một nhóm phòng ứng với một địa điểm khác nhau
    @Column(name = "address_id")
    private Long address;

}
