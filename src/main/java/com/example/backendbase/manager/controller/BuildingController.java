package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.service.BuildingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class BuildingController {

    @Autowired
    private BuildingManagerService buildingManagerService;

    @PostMapping("/add-building")
    public ResponseEntity<Object> addBuilding(@RequestBody AddBuildingRequest request){
        return ResponseUtils.httpResponse(buildingManagerService.addNewBuilding(request));
    }

    @PostMapping("/update-building")
    public ResponseEntity<Object> updateBuilding(@RequestBody UpdateBuildingRequest request){
        return ResponseUtils.httpResponse(buildingManagerService.updateBuilding(request));
    }

    @GetMapping("/get-building/{buildingId}")
    public ResponseEntity<Object> updateBuilding(@PathVariable("buildingId") Long buildingId){
        return ResponseUtils.httpResponse(buildingManagerService.getBuilding(buildingId));
        //hello 123
    }

}
