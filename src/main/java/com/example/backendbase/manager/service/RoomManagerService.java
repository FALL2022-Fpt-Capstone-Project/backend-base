package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Rooms;

import java.util.List;

public interface RoomManagerService {
    public List<Rooms> getListRoomWithFiler(Long id, String condition);
}
