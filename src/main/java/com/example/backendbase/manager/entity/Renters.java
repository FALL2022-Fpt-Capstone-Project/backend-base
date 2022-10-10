package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
@Entity @DynamicUpdate @DynamicInsert @Table(name = "Manager.Renters")
// Bảng này lưu trữ đầy đủ thông tin cơ bản của khách đi thuê để ở
public class Renters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renter_id")
    private Long id;

    @Column(name = "renter_fullname")
    private String fullName;

    @Column(name = "renter_gender")
    private String gender;

    @Column(name = "renter_phone_number")
    private String phoneNumber;

    @Column(name = "renter_email")
    private String email;

    @Column(name = "renter_identity_number")
    private String identityNumber;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    //One to one with Tabel Identity
    //Một người chỉ có thể có 1 CCCD/CMND duy nhất
    @JoinColumn(name = "identity_id")
    @OneToOne()
    private Identity identity;

    @JoinColumn(name = "address_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("address_id")
    private Address address;


}
