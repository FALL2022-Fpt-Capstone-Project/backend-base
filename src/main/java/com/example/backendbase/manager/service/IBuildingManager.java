package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.entity.response.ListAllBuildingResponse;

import java.util.List;

public interface IBuildingManager {

    Buildings addNewBuilding(AddBuildingRequest request);

    List<ListAllBuildingResponse> getAllBuilding();

    Buildings getBuilding(Long buildingId);

     String updateBuilding(UpdateBuildingRequest request);
}
