package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ContractRepo extends JpaRepository<Contracts, Long> {
    List<Contracts> findAllByGroupId(Long id);

    Contracts findByGroupId(Long id);

    @Query("SELECT c FROM Contracts c WHERE c.endDate BETWEEN :now AND :condition")
    List<Contracts> findAllByAlmostExpired(@Param("now") Timestamp now,
                                           @Param("condition") Timestamp condition);

    @Query("SELECT c FROM Contracts c WHERE c.startDate BETWEEN :now AND :condition")
    List<Contracts> findAllByLatest(@Param("now") Timestamp now,
                                    @Param("condition") Timestamp condition);
}
