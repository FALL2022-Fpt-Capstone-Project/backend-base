package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.service.RoomManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomManagerService roomManagerService;

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getRoomByGroup(@PathVariable String groupId,
                                            @RequestParam(defaultValue = "all", required = false) String floor){
        return ResponseUtils.httpResponse(roomManagerService.getListRoomWithFiler(Long.parseLong(groupId), floor));
    }
}
