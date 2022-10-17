package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Manager.ServiceType")
@DynamicInsert
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_type_id")
    @JsonProperty("service_type_id")
    private Long id;

    @Column(name = "service_type_name")
    @JsonProperty("service_type_name")
    private String name;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();
}
