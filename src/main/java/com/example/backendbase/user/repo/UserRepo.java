package com.example.backendbase.user.repo;

import com.example.backendbase.security.enums.ERole;
import com.example.backendbase.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateDesc(boolean isOwner, List<ERole> roles, boolean isActivate);

    List<User> findAllByIsOwnerAndRoles_NameInAndIsDeactiveOrderByCreatedDateAsc(boolean isOwner, List<ERole> roles, boolean isActivate);

    List<User> findAllByIsOwnerAndRoles_NameInOrderByCreatedDateDesc(boolean isOwner, List<ERole> roles);

    List<User> findAllByIsOwnerAndRoles_NameInOrderByCreatedDateAsc(boolean isOwner, List<ERole> roles);

    @Query(value = "SELECT *\n" +
            "FROM tb_user u\n" +
            "         LEFT OUTER JOIN tb_user_role ur on ur.user_id = u.id\n" +
            "         LEFT OUTER JOIN tb_role tr on tr.id = ur.role_id\n" +
            "WHERE name in (:eRole)\n" +
            "  AND u.is_owner = false\n" +
            "  AND u.is_deactive = :deactivate\n" +
            "  AND u.created_date between :startDate and :endDate\n" +
            "ORDER BY created_date desc\n", nativeQuery = true)
    List<User> findAllStaffWithFilterStatusOrderDsc(@Param("eRole") List<String> eRole,
                                                    @Param("deactivate") boolean isActive,
                                                    @Param("startDate") Timestamp startDate,
                                                    @Param("endDate") Timestamp endDate);

    @Query(value = "SELECT *\n" +
            "FROM tb_user u\n" +
            "         LEFT OUTER JOIN tb_user_role ur on ur.user_id = u.id\n" +
            "         LEFT OUTER JOIN tb_role tr on tr.id = ur.role_id\n" +
            "WHERE name in :eRole\n" +
            "  AND u.is_owner = false\n" +
            "  AND u.is_deactive = :deactivate\n" +
            "  AND u.created_date between :startDate and :endDate\n" +
            "ORDER BY created_date ASC \n", nativeQuery = true)
    List<User> findAllStaffWithFilterStatusOrderAsc(@Param("eRole") List<String> eRole,
                                                    @Param("deactivate") boolean isActive,
                                                    @Param("startDate") Timestamp startDate,
                                                    @Param("endDate") Timestamp endDate);

    @Query(value = "SELECT *\n" +
            "FROM tb_user u\n" +
            "         LEFT OUTER JOIN tb_user_role ur on ur.user_id = u.id\n" +
            "         LEFT OUTER JOIN tb_role tr on tr.id = ur.role_id\n" +
            "WHERE name in (:eRole)\n" +
            "  AND u.is_owner = false\n" +
            "  AND u.created_date between :startDate and :endDate\n" +
            "ORDER BY created_date desc\n", nativeQuery = true)
    List<User> findAllStaffWithFilterOrderDsc(@Param("eRole") List<String> eRole,
                                              @Param("startDate") Timestamp startDate,
                                              @Param("endDate") Timestamp endDate);

    @Query(value = "SELECT *\n" +
            "FROM tb_user u\n" +
            "         LEFT OUTER JOIN tb_user_role ur on ur.user_id = u.id\n" +
            "         LEFT OUTER JOIN tb_role tr on tr.id = ur.role_id\n" +
            "WHERE name in :eRole\n" +
            "  AND u.is_owner = false\n" +
            "  AND u.created_date between :startDate and :endDate\n" +
            "ORDER BY created_date ASC \n", nativeQuery = true)
    List<User> findAllStaffWithFilterOrderAsc(@Param("eRole") List<String> eRole,
                                              @Param("startDate") Timestamp startDate,
                                              @Param("endDate") Timestamp endDate);


    List<User> findAllByIsOwner(boolean isOwner);
    List<User> findAllByOrderByCreatedDateDesc();

    User deleteUserByUsername(String username);


}
