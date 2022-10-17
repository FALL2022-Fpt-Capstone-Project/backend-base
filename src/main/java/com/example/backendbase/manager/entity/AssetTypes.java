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
@Table(name = "Manager.Asset_Types")
@DynamicUpdate
@DynamicInsert
public class AssetTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_type_id")
    @JsonProperty("asset_type_id")
    private Long id;

    @Column(name = "asset_type_name")
    @JsonProperty("asset_type_name")
    private String name;

    @Column(name = "asset_type_show_name")
    @JsonProperty("asset_type_show_name")
    private String showName;

    @Column(name = "created_by")
    @JsonIgnore
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    @JsonIgnore
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

}
