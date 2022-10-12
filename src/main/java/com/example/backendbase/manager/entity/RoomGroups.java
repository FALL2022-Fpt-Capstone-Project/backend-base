package com.example.backendbase.manager.entity;

import com.example.backendbase.common.utils.TimeUtils;
import com.example.backendbase.user.util.CurrentUserUtils;
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
@Table(name = "Manager.RoomGroups")
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

    /*Group level ở đây sẽ có 3 cấp độ khác nhau :
        + BY-SINGLE : Đi thuê riêng rẽ 1 phòng
        + BY-FLOOR : Đi thuê riêng lẻ các tầng : Mỗi tầng -> Nhiều phòng
        + BY-AREA : Đi thuê cả khu (Area ở đây là một phần của BUILDING) -> Nhiều tầng -> Nhiều phòng
        + BY-BUILDING: Đi thuê cả tòa nhà (1 BUILDING ở đây có thể có một hoặc nhiều AREA) -> Nhiều tòa -> Nhiều tầng -> Nhiều phòng
     */
    @Column(name = "group_level")
    private String groupLevel;

    @Column(name = "created_by")
    private String createdBy = CurrentUserUtils.getCurrentUser();

    @Column(name = "updated_time")
    private Timestamp updatedTime = TimeUtils.getCurrentTime();

    //One to one with Table Address
    //Một nhóm phòng ứng với một địa điểm khác nhau
    @Column(name = "address_id")
    private Long address;

}
