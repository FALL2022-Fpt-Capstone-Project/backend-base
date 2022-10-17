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

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
@Entity @Table(name = "Manager.Identity") @DynamicUpdate @DynamicInsert
// Bảng này lưu trữ hình ảnh mặt trước và mặt sau CCCD hoặc CMND
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identity_id")
    private Long id;

    @Column(name = "identity_front_img")
    @JsonProperty("identity_front_img")
    private String identityFrontImg;

    @Column(name = "identity_back_img")
    @JsonProperty("identity_back_img")
    private String identityBackImg;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    @OneToOne(mappedBy = "identity")
    @JsonIgnore
    private Renters renters;
}
