package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Renters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepo extends JpaRepository<Renters, Long> {

}
