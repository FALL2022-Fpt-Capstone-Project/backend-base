package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepo extends JpaRepository<Buildings, Long> {

    @Query(" FROM Buildings ")
    List<Buildings> getAllBuilding();
}
