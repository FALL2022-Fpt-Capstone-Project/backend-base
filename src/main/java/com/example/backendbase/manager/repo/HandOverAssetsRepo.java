package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.HandOverAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandOverAssetsRepo extends JpaRepository<HandOverAssets, Long> {

    @Query()
    List<HandOverAssets> findAllByContractId(Long id);
}
