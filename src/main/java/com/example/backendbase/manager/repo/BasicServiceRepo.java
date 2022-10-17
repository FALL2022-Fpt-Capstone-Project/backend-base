package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.BasicService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicServiceRepo extends JpaRepository<BasicService, Long> {
}
