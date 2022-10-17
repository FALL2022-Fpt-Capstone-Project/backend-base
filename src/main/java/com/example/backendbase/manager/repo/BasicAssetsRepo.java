package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.BasicAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicAssetsRepo extends JpaRepository<BasicAssets, Long> {
}
