package com.example.backendbase.manager.controller;

import com.example.backendbase.common.utils.ResponseUtils;
import com.example.backendbase.manager.entity.request.AddBuildingRequest;
import com.example.backendbase.manager.entity.request.UpdateBuildingRequest;
import com.example.backendbase.manager.service.BuildingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager/group")
public class BuildingController {

    @Autowired
    private BuildingManagerService buildingManagerServiceServiceImpl;

    @PostMapping("/add-building")
    public ResponseEntity<Object> addBuilding(@RequestBody AddBuildingRequest request){
        return ResponseUtils.httpResponse(buildingManagerServiceServiceImpl.addNewBuilding(request));
    }

    @PostMapping("/update-building-information")
    public ResponseEntity<Object> updateBuilding(@RequestBody UpdateBuildingRequest request){
        return ResponseUtils.httpResponse(buildingManagerServiceServiceImpl.updateBuilding(request));
    }

    @GetMapping("/get-building-information/{Id}")
    public ResponseEntity<Object> getBuilding(@PathVariable("Id") Long buildingId){
        return ResponseUtils.httpResponse(buildingManagerServiceServiceImpl.getBuilding(buildingId));
    }

    @GetMapping("/get-group/{groupId}")
    public ResponseEntity<Object> getGroupById(@PathVariable Long groupId){
        return ResponseUtils.httpResponse(buildingManagerServiceServiceImpl.getGroupById(groupId));
    }

    @GetMapping("/get-group")
    public ResponseEntity<Object> getAllGroup(){
        return ResponseUtils.httpResponse(buildingManagerServiceServiceImpl.getAllBuilding());
    }

}
