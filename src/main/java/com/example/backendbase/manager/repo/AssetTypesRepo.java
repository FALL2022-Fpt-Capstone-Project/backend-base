package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.AssetTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTypesRepo extends JpaRepository<AssetTypes, Long> {
}
