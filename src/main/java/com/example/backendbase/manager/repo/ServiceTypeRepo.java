package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepo extends JpaRepository<ServiceType, Long> {
}
