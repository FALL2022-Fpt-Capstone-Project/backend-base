package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.service.BuildingManagerService;
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

    @PostMapping("/update-building-information")
    public ResponseEntity<Object> updateBuilding(@RequestBody UpdateBuildingRequest request){
        return ResponseUtils.httpResponse(buildingManagerService.updateBuilding(request));
    }

    @GetMapping("/get-building-information/{Id}")
    public ResponseEntity<Object> getBuilding(@PathVariable("Id") Long buildingId){
        return ResponseUtils.httpResponse(buildingManagerService.getBuilding(buildingId));
    }

}
