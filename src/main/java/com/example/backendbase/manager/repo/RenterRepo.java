package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Renters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenterRepo extends JpaRepository<Renters, Long> {

    List<Renters> findAllByRoomId(Long roomId);
}
