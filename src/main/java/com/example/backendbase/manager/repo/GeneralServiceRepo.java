package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.GeneralService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralServiceRepo extends JpaRepository<GeneralService, Long> {
    List<GeneralService> findAllByConntractId(Long contractId);

}
