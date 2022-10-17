package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.HandOverAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandOverAssetsRepo extends JpaRepository<HandOverAssets, Long> {
}
