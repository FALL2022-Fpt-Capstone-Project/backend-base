package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.RoomGroups;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.entity.response.ListAllBuildingResponse;
import com.example.backendbase.manager.entity.response.RoomGroupResponse;

import java.util.List;

public interface BuildingManagerService {

    Buildings addNewBuilding(AddBuildingRequest request);

    List<RoomGroupResponse> getAllBuilding();

    Buildings getBuilding(Long buildingId);

     String updateBuilding(UpdateBuildingRequest request);

    RoomGroupResponse getGroupById(Long groupId);
}
