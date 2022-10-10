package com.example.backendbase.manager.entity;


import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
@Entity @Table(name = "Manager.Contracts") @DynamicInsert @DynamicUpdate
// Bảng này lưu trữ đầy đủ thông tin của hợp đồng
// Bảng này lưu trữ cả hình ảnh của hợp đồng
// Bảng này lưu trữ cho cả hợp đồng của khách ở và khách đi thuê để kinh doanh (chủ trọ vs thầu)
public class Contracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_price")
    private Double price;

    @Column(name = "contract_deposit")
    private Double deposit;

    @Column(name = "contract_bill_cycle")
    private Integer billCycle;

    @Column(name = "contract_payment_cycle")
    private Integer paymentCycle;

    @Column(name = "contract_start_date")
    private Timestamp startDate;

    @Column(name = "contract_end_date")
    private Timestamp endDate;

    @Column(name = "contract_note")
    private String note;

    @Column(name = "contract_term")
    private Integer contractTerm;

    @Column(name = "contract_is_disable")
    @ColumnDefault("FALSE")
    private Boolean isDisable;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    //One to one with Table Manager.Renters
    //Một người thuê đứng ra ký tên với một hợp đồng
    @Column(name = "renter_id")
    private Long renters;

    @Column(name = "room_id")
    private Long room;

    @Column(name = "group_id")
    private Long groupId;

}
