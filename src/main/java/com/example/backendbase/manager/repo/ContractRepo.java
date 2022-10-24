package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ContractRepo extends JpaRepository<Contracts, Long> {
    List<Contracts> findAllByGroupId(Long id);

    Contracts findByGroupIdAndContractTerm(Long id, Integer contractTerm);

    List<Contracts> findAllByGroupIdAndContractTerm(Long id, Integer contractTerm);

    Contracts findByGroupId(Long id);

    @Query("SELECT c FROM Contracts c WHERE c.endDate BETWEEN :now AND :condition AND c.contractTerm = 1 AND c.groupId = :groupId")
    List<Contracts> findAllByAlmostExpired(@Param("now") Timestamp now,
                                           @Param("condition") Timestamp condition,
                                           @Param("groupId") Long id);

    @Query("SELECT c FROM Contracts c WHERE c.startDate BETWEEN :now AND :condition AND c.contractTerm = 1 AND c.groupId = :groupId")
    List<Contracts> findAllByLatest(@Param("now") Timestamp now,
                                    @Param("condition") Timestamp condition,
                                    @Param("groupId") Long id);

    @Query("SELECT c FROM Contracts c WHERE c.endDate < :now OR c.isDisable = TRUE AND c.contractTerm = 0 AND c.groupId = :groupId")
    List<Contracts> findAllExpriedContract(@Param("now") Timestamp now, @Param("groupId") Long id);
}
