package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.RoomGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<RoomGroups, Long> {
}
