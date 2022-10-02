package com.example.backendbase.manager.service;

import com.example.backendbase.manager.entity.Buildings;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;

import java.util.List;

public interface IBuildingManager {

    Buildings addNewBuilding(AddBuildingRequest request);

    List<Buildings> getAllBuilding();
}
