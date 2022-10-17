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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "Manager.BasicAssets")
@DynamicUpdate
@DynamicInsert
public class BasicAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_id")
    @JsonProperty("asset_id")
    private Long id;

    @Column(name = "asset_name")
    @JsonProperty("asset_name")
    private String name;

    @Column(name = "asset_unit")
    @JsonProperty("asset_unit")
    private String unit;

    @Column(name = "asset_type_id")
    @JsonProperty("asset_type_id")
    private String type;

    @Column(name = "created_by")
    @JsonIgnore
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    @JsonIgnore
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

}
