package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.HandOverGeneralService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandOverGeneralServiceRepo extends JpaRepository<HandOverGeneralService, Long> {
    HandOverGeneralService findByContractIdAndGeneralServiceId(Long contractId, Long serviceId);
}
