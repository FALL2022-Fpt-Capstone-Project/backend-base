package com.example.backendbase.manager.entity;


import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "Manager.BasicService")
@DynamicInsert @DynamicUpdate
public class BasicService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    @JsonProperty("service_id")
    private String id;

    @Column(name = "service_name")
    @JsonProperty("service_name")
    private String name;

    @Column(name = "service_show_name")
    @JsonProperty("service_show_name")
    private String showName;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();
}
