package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.service.IBuildingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class BuildingController {

    @Autowired
    private IBuildingManager buildingManagerServiceImpl;

    @PostMapping("/add-building")
    public ResponseEntity<Object> addBuilding(@RequestBody AddBuildingRequest request){
        return ResponseUtils.httpResponse(buildingManagerServiceImpl.addNewBuilding(request));
    }

    @GetMapping("/get-list-building")
    public ResponseEntity<Object> getListBuilding(){
        return ResponseUtils.httpResponse(buildingManagerServiceImpl.getAllBuilding());
    }


}
