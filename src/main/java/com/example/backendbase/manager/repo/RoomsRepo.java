package com.example.backendbase.manager.repo;

import com.example.backendbase.manager.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepo extends JpaRepository<Rooms, Long> {

    List<Rooms> findAllByRoomGroups(Long id);

    List<Rooms> findAllByRoomGroupsAndFloor(Long id, int floor);
}
