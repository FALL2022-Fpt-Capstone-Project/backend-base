package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Rooms;
import com.example.backendbase.manager.repo.RoomsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomManagerServiceImpl implements RoomManagerService {

    private final RoomsRepo roomsRepo;
    @Override
    public List<Rooms> getListRoomWithFiler(Long id, String condition) {
        if (condition.equals("all")) return roomsRepo.findAllByRoomGroups(id);

        return roomsRepo.findAllByRoomGroupsAndFloor(id, Integer.parseInt(condition));
    }
}
